// ** I18N

// Calendar CS language
// Author: NELASOFT Technologies, s.r.o., <info@nelasoft.cz>
// Encoding: UTF-8
// Distributed under the same terms as the calendar itself.


// full day names

Calendar._DN = new Array ("Nedìle", "Pondìlí", "Úterı", "Støeda", "Ètvrtek", "Pátek", "Sobota", "Nedìle");



// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.


// short day names

Calendar._SDN = new Array

("Ne", "Po", "Út", "St", "Èt", "Pá", "So", "Ne");



// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.

Calendar._FD = 0;

// full month names
Calendar._MN = new Array

("Leden", "Únor", "Bøezen", "Duben", "Kvìten", "Èerven", "Èervenec", "Srpen", "Záøí", "Øíjen", "Listopad", "Prosinec");



// short month names

Calendar._SMN = new Array

("Led", "Úno", "Bøe", "Dub", "Kvì", "Èer", "Èer", "Srp", "Záø", "Øíj", "Lis", "Pro");



// tooltips

Calendar._TT = {};

Calendar._TT["INFO"] = "About the calendar";



Calendar._TT["ABOUT"] =

"DHTML Date/Time Selector\n" +

"(c) dynarch.com 2002-2005 / Author: Mihai Bazon\n" + // don't translate this this ;-)

"For latest version visit: http://www.dynarch.com/projects/calendar/\n" +

"Distributed under GNU LGPL.  See http://gnu.org/licenses/lgpl.html for details." +

"\n\n" +

"Vıbìr datumu:\n" +

"- Pouijte tlaèítka \xab, \xbb k vıbìru roku\n" +

"- Pouijte tlaèítka " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " k vıbìru mìsíce\n" +

"- Podrte tlaèítko myši na jakémkoliv z tlaèítek pro rychlejší vıbìr."; 



Calendar._TT["PREV_YEAR"] = "Pøedchozí rok (pøidr pro menu)";
Calendar._TT["PREV_MONTH"] = "Pøedchozí mìsíc (pøidr pro menu)";
Calendar._TT["GO_TODAY"] = "Dnešní datum";
Calendar._TT["NEXT_MONTH"] = "Další mìsíc (pøidr pro menu)";
Calendar._TT["NEXT_YEAR"] = "Další rok (pøidr pro menu)";
Calendar._TT["SEL_DATE"] = "Vyber datum";
Calendar._TT["DRAG_TO_MOVE"] = "Chy a táhni, pro pøesun";
Calendar._TT["PART_TODAY"] = " (dnes)";



// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.

Calendar._TT["DAY_FIRST"] = "Zobraz %s první";


// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.

Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "Zavøít";
Calendar._TT["TODAY"] = "Dnes";
Calendar._TT["TIME_PART"] = "Klikni/táhni pro zmìnu hodnoty";


// date formats

Calendar._TT["DEF_DATE_FORMAT"] = "d.m.yy";
Calendar._TT["TT_DATE_FORMAT"] = "%a, %b %e";

Calendar._TT["WK"] = "wk";
Calendar._TT["TIME"] = "Èas:";
