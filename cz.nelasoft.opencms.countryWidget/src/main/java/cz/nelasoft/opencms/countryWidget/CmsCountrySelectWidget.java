package cz.nelasoft.opencms.countryWidget;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.opencms.file.CmsObject;
import org.opencms.main.CmsLog;
import org.opencms.widgets.CmsSelectWidget;
import org.opencms.widgets.CmsSelectWidgetOption;
import org.opencms.widgets.I_CmsWidget;
import org.opencms.widgets.I_CmsWidgetDialog;
import org.opencms.widgets.I_CmsWidgetParameter;

/**
 * This class subclass standard CmsSelectWidget and provides standard
 * CmsSelectWidget with pre-filled data of country codes. Currently it supports
 * czech and english localization.
 * 
 * @author NELASOFT Technlogies s.r.o.
 * @author Jakub Jecminek
 * 
 */
public class CmsCountrySelectWidget extends CmsSelectWidget {

	private static final Log log = CmsLog.getLog(CmsCountrySelectWidget.class);

	/**
	 * 
	 */
	private static final Locale LOCALE_CS = new Locale("cs");
	
	private static final String CODE_CONFIGURATION = "wordCode";

	private CountryCodesReader codesReader;

	public CmsCountrySelectWidget() {
		super();
	}

	public CmsCountrySelectWidget(String configuration) {
		super(configuration);
	}

	/**
	 * This method is used to create standard configuration string that is
	 * required by CmsSelectWidget.
	 * 
	 * @param cms
	 *            current CmsObject.
	 * @return String in format key1:value1 | key2:value2
	 */
	private String createConfiguration(CmsObject cms, I_CmsWidgetParameter param) {		
		Locale dialogContentLocale = getCurrentLocale(cms);

		Map<String, String> countryCodes = getCountryCodesBasedOnLocale(dialogContentLocale);
		StringBuffer result = new StringBuffer();
		int size = countryCodes.size();
		int i = 0;
		for (Map.Entry<String, String> entry : countryCodes.entrySet()) {
			result.append(entry.getKey());
			result.append(":");
			result.append(entry.getValue());
			if (i < size - 1) {
				result.append("|");
			}
			i++;
		}
		return result.toString();
	}

	/**
	 * This methods tries to determine witch locale should be used. If the xsd
	 * does not contains information about locale the default will be used.
	 * 
	 * @param cms
	 * @param param
	 * @return
	 */
	private Locale getCurrentLocale(CmsObject cms) {
		return cms.getRequestContext().getLocale();
	}

	@Override
	protected List<CmsSelectWidgetOption> parseSelectOptions(CmsObject cms,
			I_CmsWidgetDialog widgetDialog, I_CmsWidgetParameter param) {
		if (getSelectOptions() == null) {
			String configuration = createConfiguration(cms, param);
			setSelectOptions(CmsSelectWidgetOption.parseOptions(configuration));

		}
		return getSelectOptions();
	}

	/**
	 * Helper method wich checks CmsObject's requestContext and determinates
	 * witch locale is currently in use. If CS is active locale, the returned
	 * country codes will have proper czech names of countries. Otherwise it
	 * returns default(english) names.
	 * 
	 * @param cms
	 * @return
	 */
	private Map<String, String> getCountryCodesBasedOnLocale(Locale locale) {
		String configuration = getConfiguration();
		// use default two word code
		int numberOfWords = getWordCodeConfiguration();
		codesReader = new CountryCodesReader(numberOfWords);
		Map<String, String> countryCodes = null;
		if (locale != null && locale.equals(LOCALE_CS)) {
			countryCodes = codesReader.getLanguageCodesCS();
		} else {
			// get default english version
			countryCodes = codesReader.getLanguageCodesEN();
		}
		return countryCodes;
	}

	private int getWordCodeConfiguration() {
		// defaut is two therefore if nothing is set return that
		int numberOfWords = CountryCodesReader.TWO_WORD_CODE;
		List<CmsSelectWidgetOption> parseOptions = CmsSelectWidgetOption
				.parseOptions(getConfiguration());
		for (CmsSelectWidgetOption option : parseOptions) {
			if (option.getValue().equals(CODE_CONFIGURATION)) {
				String wordCode = option.getOption().trim();
				if (wordCode.matches(".*\\d.*")) {
					numberOfWords = Integer.parseInt(wordCode);
				}
			}
		}
		return numberOfWords;
	}

	@Override
	public I_CmsWidget newInstance() {
		return new CmsCountrySelectWidget(getConfiguration());
	}

}
