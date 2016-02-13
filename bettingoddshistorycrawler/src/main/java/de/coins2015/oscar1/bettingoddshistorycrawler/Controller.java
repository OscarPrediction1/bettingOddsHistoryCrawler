package de.coins2015.oscar1.bettingoddshistorycrawler;

public class Controller {

    public static void main(String[] args) throws Exception {

	String host = args[0];
	String port = args[1];
	String userName = args[2];
	String password = args[3];
	String database = args[4];

	String[] pages = {

		// 2006
		// "https://web.archive.org/web/20060203020654/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1864782x/sid/788394",
		// "https://web.archive.org/web/20060203035551/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1864784x/sid/788394",
		// "https://web.archive.org/web/20060203035442/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1864780x/sid/788394",
		// "https://web.archive.org/web/20060203015241/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1864781x/sid/788394",
		// "https://web.archive.org/web/20060203032110/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1928050x/sid/788394",
		// "https://web.archive.org/web/20060203031953/http://www.oddschecker.com/betting/mode/o/card/specials-awards/odds/1928051x/sid/788394",

		// early

		// best-pic
		// director
		// actor
		// actress
		// supp-actor
		// supp-actress

		// 2007
		"https://web.archive.org/web/20070222122612/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3340520x/sid/1105200",
		"https://web.archive.org/web/20070222101212/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3554269x/sid/1144924",
		"https://web.archive.org/web/20070221031519/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3340524x/sid/1105201",
		"https://web.archive.org/web/20070221031529/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3340528x/sid/1105202",
		"https://web.archive.org/web/20070222101451/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3634883x/sid/1163279",
		"https://web.archive.org/web/20070221031733/http://www.oddschecker.com/betting/mode/o/card/specials-oscars/odds/3634866x/sid/1163280",

		// early

		// best-pic
		// director
		// actor
		// actress
		// supp-actor
		// supp-actress

		// 2008

		"https://web.archive.org/web/20080219232248/http://www.oddschecker.com/specials/oscars/best-film",

		"https://web.archive.org/web/20080224212219/http://www.oddschecker.com/specials/oscars/best-actor",

		"https://web.archive.org/web/20080224090429/http://www.oddschecker.com/specials/oscars/best-actress",

		// early

		// best-pic
		// director
		// actor
		// actress
		// supp-actor
		// supp-actress

		// 2009
		"https://web.archive.org/web/20090221053319/http://www.oddschecker.com/specials/oscars/best-director",
		"https://web.archive.org/web/20090221233424/http://www.oddschecker.com/specials/oscars/best-actor",
		"https://web.archive.org/web/20090130104743/http://www.oddschecker.com/specials/oscars/best-actress",
		"https://web.archive.org/web/20090221053416/http://www.oddschecker.com/specials/oscars/best-supporting-actress",

		// early

		// best-pic
		// director
		// actor
		// actress
		// supp-actor
		// supp-actress

		// 2010
		"https://web.archive.org/web/20100216082629/http://www.oddschecker.com/specials/awards/oscars/best-film",
		"https://web.archive.org/web/20100205230507/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20100205230502/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20100301234523/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20100205230514/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20100205231538/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// early

		// best-pic
		"https://web.archive.org/web/20100113135950/http://www.oddschecker.com/specials/awards/oscars/best-film",
		// director
		"https://web.archive.org/web/20100125110515/http://www.oddschecker.com/specials/awards/oscars/best-director",
		// actor
		"https://web.archive.org/web/20091228123324/http://www.oddschecker.com/specials/awards/oscars/best-actor/win-market",
		// actress
		"https://web.archive.org/web/20100125113538/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		// supp-actor
		// supp-actress
		"https://web.archive.org/web/20100125192133/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// 2011
		"https://web.archive.org/web/20110219044455/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20110227042827/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20110221004128/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20110223101005/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20110207233817/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20110220201206/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// early

		// best-pic
		"https://web.archive.org/web/20110127160210/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20110109125256/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20101226232656/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20101126021347/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20101026065240/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		// director
		"https://web.archive.org/web/20110109125723/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20101203101454/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20101103020805/http://www.oddschecker.com/specials/awards/oscars/best-director",
		// actor
		"https://web.archive.org/web/20110103010649/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20101203101449/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20101103020757/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		// actress
		"https://web.archive.org/web/20110108045802/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20101127045100/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		// supp-actor
		"https://web.archive.org/web/20110108054315/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20101223032344/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		// supp-actress
		"https://web.archive.org/web/20110131020549/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20101223032358/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// 2012
		"https://web.archive.org/web/20120213230749/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20120215040414/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20120213230403/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20120215040544/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20120213201801/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20120213201806/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// early

		// best-pic
		"https://web.archive.org/web/20120115130518/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20111214025022/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		"https://web.archive.org/web/20111113180632/http://www.oddschecker.com/specials/awards/oscars/best-picture",
		// director
		"https://web.archive.org/web/20120115132121/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20111214025956/http://www.oddschecker.com/specials/awards/oscars/best-director",
		"https://web.archive.org/web/20111113180626/http://www.oddschecker.com/specials/awards/oscars/best-director",
		// actor
		"https://web.archive.org/web/20120115121608/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20111214025340/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		"https://web.archive.org/web/20111113180614/http://www.oddschecker.com/specials/awards/oscars/best-actor",
		// actress
		"https://web.archive.org/web/20120115104447/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20111214025951/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		"https://web.archive.org/web/20111113180620/http://www.oddschecker.com/specials/awards/oscars/best-actress",
		// supp-actor
		"https://web.archive.org/web/20110909080629/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actor",
		// supp-actress
		"https://web.archive.org/web/20110910025429/http://www.oddschecker.com/specials/awards/oscars/best-supporting-actress",

		// 2013
		"https://web.archive.org/web/20130224105832/http://www.oddschecker.com/awards/oscars/oscars/best-picture",
		"https://web.archive.org/web/20130202020643/http://www.oddschecker.com/awards/oscars/oscars/best-director",
		"https://web.archive.org/web/20130224110835/http://www.oddschecker.com/awards/oscars/oscars/best-actor",
		"https://web.archive.org/web/20130224104859/http://www.oddschecker.com/awards/oscars/oscars/best-actress",
		"https://web.archive.org/web/20130131060417/http://www.oddschecker.com/awards/oscars/oscars/best-supporting-actor",
		"https://web.archive.org/web/20130127001836/http://www.oddschecker.com/awards/oscars/oscars/best-supporting-actress",

		// early

		// best-picture
		"https://web.archive.org/web/20121128002853/http://www.oddschecker.com/awards/oscars/oscars/best-picture",
		"https://web.archive.org/web/20121120003931/http://www.oddschecker.com/awards/oscars/best-picture",

		// director
		"https://web.archive.org/web/20121129044014/http://www.oddschecker.com/awards/oscars/oscars/best-director",
		"https://web.archive.org/web/20130102061738/http://www.oddschecker.com/awards/oscars/oscars/best-director",

		// actor
		"https://web.archive.org/web/20121128002848/http://www.oddschecker.com/awards/oscars/oscars/best-actor",
		"https://web.archive.org/web/20130102023715/http://www.oddschecker.com/awards/oscars/oscars/best-actor",

		// actress
		"https://web.archive.org/web/20121128000733/http://www.oddschecker.com/awards/oscars/oscars/best-actress",
		"https://web.archive.org/web/20130129032925/http://www.oddschecker.com/awards/oscars/oscars/best-actress",
		"https://web.archive.org/web/20130102023419/http://www.oddschecker.com/awards/oscars/oscars/best-actress",

		// supp-actor

		// supp-actress

		// 2014
		"https://web.archive.org/web/20140228110143/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20140219064710/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20140228110210/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20140220152203/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20140201161810/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20140119184730/http://www.oddschecker.com/awards/oscars/best-supporting-actress",

		// early
		// best-pic
		"https://web.archive.org/web/20131210101609/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20131110221930/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20131010232142/http://www.oddschecker.com/awards/oscars/best-picture",

		// best-director
		"https://web.archive.org/web/20131221032316/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20131120024657/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20131019165952/http://www.oddschecker.com/awards/oscars/best-director",

		// best-actor
		"https://web.archive.org/web/20131205054401/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20131026204345/http://www.oddschecker.com/awards/oscars/best-actor",

		// best-actress
		"https://web.archive.org/web/20131229043628/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20131126181859/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20131026204350/http://www.oddschecker.com/awards/oscars/best-actress",

		// best-supporting-actress
		"https://web.archive.org/web/20131221024758/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20131212160209/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20131126181052/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20131026163918/http://www.oddschecker.com/awards/oscars/best-supporting-actress",

		// 2015
		"https://web.archive.org/web/20150221064044/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20150222144552/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20150221064038/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20150222144546/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20150222172032/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20150217124721/http://www.oddschecker.com/awards/oscars/best-supporting-actress",

		// early
		// best-pic
		"https://web.archive.org/web/20150102131010/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20141218122004/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20141119113911/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20141102200529/http://www.oddschecker.com/awards/oscars/best-picture",
		"https://web.archive.org/web/20141006084355/http://www.oddschecker.com/awards/oscars/best-picture",

		// best-director
		"https://web.archive.org/web/20141117011639/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20141118014150/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20141218114322/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20150108123448/http://www.oddschecker.com/awards/oscars/best-director",
		"https://web.archive.org/web/20150121144931/http://www.oddschecker.com/awards/oscars/best-director",

		// best-actor
		"https://web.archive.org/web/20150107064823/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20150120092220/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20141218113604/http://www.oddschecker.com/awards/oscars/best-actor",
		"https://web.archive.org/web/20141113182424/http://www.oddschecker.com/awards/oscars/best-actor",

		// best-actress
		"https://web.archive.org/web/20141117011212/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20141218121959/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20150108163715/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20150121144451/http://www.oddschecker.com/awards/oscars/best-actress",
		"https://web.archive.org/web/20150128184410/http://www.oddschecker.com/awards/oscars/best-actress",

		// best-supporting-actor
		"https://web.archive.org/web/20150130171508/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20150109110650/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20150121144341/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20141218110457/http://www.oddschecker.com/awards/oscars/best-supporting-actor",
		"https://web.archive.org/web/20141112045428/http://www.oddschecker.com/awards/oscars/best-supporting-actor",

		// best supporting-actress
		"https://web.archive.org/web/20141116220352/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20141218114638/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20150110072954/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20150128201530/http://www.oddschecker.com/awards/oscars/best-supporting-actress",
		"https://web.archive.org/web/20150203083255/http://www.oddschecker.com/awards/oscars/best-supporting-actress"

	};

	BettingOddsHistoryCrawler bohc = new BettingOddsHistoryCrawler();

	MongoDBHandler mdbh = new MongoDBHandler(host, port, userName,
		database, password);

	mdbh.rewriteData("bettingOdds");

	// for (String page : pages) {
	// JSONObject result = bohc.visit(page);
	// mdbh.storeData(result, "bettingOdds_history");
	// }

    }
}
