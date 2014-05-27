# OpenCms Country Widget

This module provides XSD widget for OpenCms that extend standard CmsSlectWidget by listing countries. After user selects county from widget, appropriate country code is written into the xmlcontent.

## Installation
1. Import module in ZIP form through OpenCms administration as a standard module
2. Edit opencms-vfs.xml config file and add new widget to widgets section:
`<widget class="cz.nelasoft.opencms.countryWidget.CmsCountrySelectWidget" alias="cmsCountrySelectWidget" />`
3. Restart your servlet container

## Using & configuration
Configuration is done in standard way as with all other OpenCms widgets. Default countrycode is 2 char wordcode (for example for United States it is "US"). To change this and use alternative three wordcode user just have to specified different configuration string in xmlcontent's XSD:
```xml
<layouts>
	<layout element="YourCountrySelectElement" widget="cmsCountrySelectWidget" configuration="wordCode:3" />
</layouts>
```

## Build
Create ZIP module from cz.nelasoft.opencms.countryWidget folder. If you made changes in the source code, then first use maven to build new version of module lib (mvn clean install) and then replace cz.nelasoft.opencms.countryWidget/system/modules/cz.nelasoft.opencms.countryWidget/lib/cz.nelasoft.opencms.countryWidget.jar with your builded jar(target/cz.nelasoft.opencms.countryWidget.jar).
