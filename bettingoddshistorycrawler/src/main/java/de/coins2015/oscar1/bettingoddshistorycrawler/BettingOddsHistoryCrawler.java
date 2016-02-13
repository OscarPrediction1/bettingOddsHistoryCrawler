package de.coins2015.oscar1.bettingoddshistorycrawler;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BettingOddsHistoryCrawler {

    @SuppressWarnings("unchecked")
    public JSONObject visit(String url) throws IOException {

	String time = url.substring(28, 42);

	DateFormat df = new SimpleDateFormat("yyyyMMddkkmmss", Locale.ENGLISH);
	Timestamp timestamp = null;
	try {
	    Date date = df.parse(time);
	    timestamp = new Timestamp(date.getTime());
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	String category = getCategory(timestamp, url);

	Document doc = Jsoup.connect(url).timeout(20000).userAgent("Mozilla")
		.get();

	JSONObject categoryJson = new JSONObject();

	categoryJson.put("_id", time);
	categoryJson.put("category", category);
	categoryJson.put("timestamp", timestamp.toString());

	if (timestamp.getYear() >= 107 && timestamp.getYear() <= 112) {

	    String html = doc.html();

	    String tableData = html
		    .substring(html.indexOf("<tbody id=\"t1\"") + 15);
	    tableData = tableData.substring(0, tableData.indexOf("</tbody>"));

	    String[] rows = StringUtils.splitByWholeSeparatorPreserveAllTokens(
		    tableData, "</tr>");
	    JSONArray dataJson = new JSONArray();
	    for (String row : rows) {
		row = StringUtils.trim(row);
		if (row.startsWith("<tr class")) {

		    String tr = row.substring(0, row.indexOf(">"));

		    JSONObject itemJson = new JSONObject();

		    String[] columns = StringUtils
			    .splitByWholeSeparatorPreserveAllTokens(
				    row.substring(tr.length()), "</td>");
		    for (String column : columns) {
			column = StringUtils.trim(column);

			if (column.contains("_name")) {
			    String id = column.substring(
				    column.indexOf("id=\"") + 5,
				    column.indexOf("_name"));

			    itemJson.put("id", id);

			    String name = column
				    .substring(column.indexOf(">") + 1);
			    itemJson.put("name", name);
			}
			if (column.startsWith("<td id")) {

			    String bettingAgencyId = column.substring(column
				    .indexOf("id=\"") + 4);
			    bettingAgencyId = bettingAgencyId.substring(
				    bettingAgencyId.indexOf("_") + 1,
				    bettingAgencyId.indexOf("\""));

			    String odds = column.substring(
				    column.indexOf(">") + 1, column.length());

			    itemJson.put(bettingAgencyId, odds);
			}
		    }
		    dataJson.add(itemJson);
		}

		categoryJson.put("data", dataJson);
	    }

	} else if (timestamp.getYear() >= 113) {

	    String html = doc.html();

	    String tableData = html
		    .substring(html.indexOf("<tbody id=\"t1\"") + 15);
	    tableData = tableData.substring(0, tableData.indexOf("</tbody>"));

	    String[] rows = StringUtils.splitByWholeSeparatorPreserveAllTokens(
		    tableData, "</tr>");
	    JSONArray dataJson = new JSONArray();
	    for (String row : rows) {
		row = StringUtils.trim(row);
		if (row.startsWith("<tr")) {

		    String tr = row.substring(0, row.indexOf(">"));

		    String id = tr.substring(tr
			    .indexOf("data-participant-id=\""));
		    id = id.substring(id.indexOf("\"") + 1);
		    id = id.substring(0, id.indexOf("\""));

		    JSONObject itemJson = new JSONObject();
		    itemJson.put("betting_id", id);

		    String[] columns = StringUtils
			    .splitByWholeSeparatorPreserveAllTokens(
				    row.substring(tr.length()), "</td>");
		    for (String column : columns) {
			column = StringUtils.trim(column);
			if (column.contains(id + "_name")) {
			    String name = column
				    .substring(column.indexOf(">") + 1);
			    itemJson.put("name", name);
			    String boxOfficeId = "";

			    itemJson.put("boxOfficeId", boxOfficeId);
			}
			if (column.startsWith("<td id")) {

			    String bettingAgencyId = column.substring(column
				    .indexOf("id=\"") + 4);
			    bettingAgencyId = bettingAgencyId.substring(
				    bettingAgencyId.indexOf("_") + 1,
				    bettingAgencyId.indexOf("\""));

			    String odds = column.substring(
				    column.indexOf(">") + 1, column.length());

			    itemJson.put(bettingAgencyId, odds);
			}
		    }
		    dataJson.add(itemJson);
		}

		categoryJson.put("data", dataJson);
	    }

	}

	return categoryJson;
    }

    private String getCategory(Timestamp timestamp, String url) {
	String category = "";
	if (timestamp.before(new Timestamp(108, 0, 1, 0, 0, 0, 0))) {
	    if (url.contains("1864782x") || url.contains("3340520x")) {
		category = "best-picture";
	    } else if (url.contains("1864784x") || url.contains("3554269x")) {
		category = "best-director";
	    } else if (url.contains("1864780x") || url.contains("3340524x")) {
		category = "best-actor";
	    } else if (url.contains("1864781x") || url.contains("3340528x")) {
		category = "best-actress";
	    } else if (url.contains("1928050x") || url.contains("3634883x")) {
		category = "best-supporting-actor";
	    } else if (url.contains("1928051x") || url.contains("3634866x")) {
		category = "best-supporting-actress";
	    }
	} else {
	    category = url.substring(url.lastIndexOf("/") + 1);
	    if (category.equals("best-film")) {
		category = "best-picture";
	    }
	}
	return category;
    }

}
