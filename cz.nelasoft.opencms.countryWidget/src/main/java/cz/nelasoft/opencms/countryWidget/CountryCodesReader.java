package cz.nelasoft.opencms.countryWidget;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.opencms.i18n.CmsResourceBundleLoader;
import org.opencms.main.CmsLog;

/**
 * This class is used to prepare choices for widget by reading properties of
 * module which contains all possible country codes.
 * 
 * @author Nelasoft TECHNOLOGIES s.r.o.
 * 
 */
public class CountryCodesReader {

	private static final Log logger = CmsLog.getLog(CountryCodesReader.class);

	private static final String BUNDLE_BASE_NAME = "cz.nelasoft.opencms.countryWidget.messages";
	private static final Locale LOCALE_CS = new Locale("cs");
	private static final Locale LOCALE_EN = new Locale("en");

	public static final int TWO_WORD_CODE = 2;
	public static final int THREE_WORD_CODE = 3;

	private Map<String, String> countryCodes;

	private int countryCodeNumber;

	/**
	 * Use this constructor if you want to use default 2word code.
	 */
	public CountryCodesReader() {
		countryCodeNumber = TWO_WORD_CODE;
	}

	/**
	 * Use this constructor for overriding default usage of two word country
	 * codes.
	 * 
	 * @param countryCodeNumber
	 *            this determines if the maps will contains 2 word code or 3
	 *            word code.
	 */
	public CountryCodesReader(int countryCodeNumber) {
		this.countryCodeNumber = countryCodeNumber;
	}

	/**
	 * This method is used for obtaining czech labels for widget that are
	 * provided by this module.
	 * 
	 * @return Map that contains both czech labels and country codes.
	 */
	public Map<String, String> getLanguageCodesCS() {
		readCountyCsCodesFromMessages();
		return countryCodes;
	}

	/**
	 * This method is used for obtaining english labels(country names) and
	 * country codes.
	 * 
	 * @return Map that contains both english labels and country codes.
	 */
	public Map<String, String> getLanguageCodesEN() {
		readCountyEnCodesFromMessages();
		return countryCodes;
	}

	/**
	 * This method is used for decision which key should be used from bundle.
	 * Default is two char sized key. This can be changed by user and
	 * alternative three char code can be used instead.
	 * 
	 * @return
	 */
	private int determinSizeOfKey() {
		int keySize = TWO_WORD_CODE;
		if (countryCodeNumber == THREE_WORD_CODE) {
			keySize = THREE_WORD_CODE;
		}
		return keySize;
	}

	/**
	 * This method is used to load country codes from messages file that is part
	 * of resource bundle with czech locale.
	 */
	private void readCountyCsCodesFromMessages() {
		try {
			readCountryCodesFromMessages(LOCALE_CS);
		} catch (Exception e) {
			logger.error("Failed to load cs country codes from messages.", e);
		}
	}

	/**
	 * This method is used to load country codes from meesage file that is part
	 * of resource bundle with english locale(default).
	 * 
	 */
	private void readCountyEnCodesFromMessages() {
		try {
			readCountryCodesFromMessages(LOCALE_EN);
		} catch (Exception e) {
			logger.error("Failed to load en country codes from messages.", e);
		}
	}

	/**
	 * Shared private method to reduce duplicities of code. Simply reads message
	 * bundle of requested locale.
	 * 
	 * @param languageCodeMap
	 *            This map will be filled with keys and values that are in
	 *            resource bundle.
	 * @param locale
	 *            Locale that will be used to select resource bundle.
	 */
	private void readCountryCodesFromMessages(Locale locale) throws Exception {
		countryCodes = new HashMap<String, String>();
		ResourceBundle bundle = getResourceBundle(locale);
		Enumeration<String> keys = bundle.getKeys();
		// determin which key to use
		// default is two
		int keySize = determinSizeOfKey();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.length() == keySize) {
				String value = bundle.getString(key);
				countryCodes.put(key, value);
			}
		}
		// sort the map by keys
		countryCodes = new TreeMap<String, String>(countryCodes);
	}

	/**
	 * This method is used to obtain resource bundle that is part of this
	 * module.
	 * 
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ResourceBundle getResourceBundle(Locale locale) throws Exception {
		ResourceBundle bundle = CmsResourceBundleLoader.getBundle(
				BUNDLE_BASE_NAME, locale);
		return bundle;
	}

}
