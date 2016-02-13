package de.coins2015.oscar1.bettingoddshistorycrawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class MongoDBHandler {

    private static final Map<String, String> boxOfficeIds;
    private static final Map<String, Integer> won;
    private static final Map<String, Integer> oscars;
    private static final Map<String, Integer> nominations;

    static {
	boxOfficeIds = new HashMap<String, String>();
	boxOfficeIds.put("The Artist", "artist");
	boxOfficeIds.put("The Descendants", "descendents");
	boxOfficeIds.put("The Help", "help2011");
	boxOfficeIds.put("Hugo", "hugocabret");
	boxOfficeIds.put("War Horse", "warhorse");
	boxOfficeIds
		.put("Extremely Loud and Incredibly Close", "extremelyloud");
	boxOfficeIds.put("The Tree of Life", "treeoflife");
	boxOfficeIds.put("Midnight in Paris", "midnightinparis");
	boxOfficeIds.put("Moneyball", "moneyball");
	boxOfficeIds.put("Jean Dujardin - The Artist", "artist");
	boxOfficeIds.put("George Clooney - The Descendants", "descendents");
	boxOfficeIds.put("Brad Pitt - Moneyball", "moneyball");
	boxOfficeIds.put("Gary Oldman - Tinker Tailor Soldier Spy",
		"tinkertailorsoldierspy");
	boxOfficeIds.put("Demian Bichir - A Better Life", "abetterlife");
	boxOfficeIds.put("Octavia Spencer - The Help", "help2011");
	boxOfficeIds.put("Berenice Bejo - The Artist", "artist");
	boxOfficeIds.put("Jessica Chastain - The Help", "help2011");
	boxOfficeIds.put("Melissa McCarthy - Bridesmaids", "wiigapatow");
	boxOfficeIds.put("Janet McTeer - Albert Nobbs", "albertnobbs");
	boxOfficeIds.put("Tommy Lee Jones - Lincoln", "lincoln");
	boxOfficeIds.put("Phillip Seymour Hoffman - The Master", "themaster");
	boxOfficeIds.put("Christoph Waltz - Django Unchained",
		"djangounchained");
	boxOfficeIds.put("Robert DeNiro - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Alan Arkin - Argo", "argo");
	boxOfficeIds.put("Ang Lee - Life Of Pi", "lifeofpi");
	boxOfficeIds.put("Michael Haneke - Amour", "amour");
	boxOfficeIds.put("David O Russell - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Benh Zeitlin - Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Daniel Day-Lewis - Lincoln", "lincoln");
	boxOfficeIds.put("Hugh Jackman - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Joaquin Phoenix - The Master", "themaster");
	boxOfficeIds.put("Bradley Cooper - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Denzel Washington - Flight", "flight");
	boxOfficeIds.put("Jennifer Lawrence - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Emmanuelle Riva - Amour", "amour");
	boxOfficeIds.put("Jessica Chastain - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Naomi Watts - The Impossible", "impossible");
	boxOfficeIds.put("Anthony Hopkins - Hitchcock", "hitchcock");
	boxOfficeIds.put("Steven Spielberg - Lincoln", "lincoln");
	boxOfficeIds.put("12 Years a Slave", "twelveyearsaslave");
	boxOfficeIds.put("Gravity", "gravity");
	boxOfficeIds.put("American Hustle", "davido2013");
	boxOfficeIds.put("The Avengers", "avengers11");
	boxOfficeIds.put("The Wolf Of Wall Street", "wolfofwallstreet");
	boxOfficeIds.put("Captain Phillips", "captainphillips");
	boxOfficeIds.put("Nebraska", "nebraska");
	boxOfficeIds.put("Her", "her2013");
	boxOfficeIds.put("Philomena", "philomena");
	boxOfficeIds.put("Anne Hathaway - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Sally Field - Lincoln", "lincoln");
	boxOfficeIds.put("Helen Hunt - The Sessions", "sixsessions");
	boxOfficeIds.put("Amy Adams - The Master", "themaster");
	boxOfficeIds.put("Jacki Weaver - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Sally Hawkins - Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("Alfonso Cuaron - Gravity", "gravity");
	boxOfficeIds.put("Steve McQueen - 12 Years A Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("David O Russell - American Hustle", "davido2013");
	boxOfficeIds.put("Beasts Of The Southern Wild - Benh Zeitlin",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Alexander Payne - Nebraska", "nebraska");
	boxOfficeIds.put("Matthew McConaughey - The Dallas Buyers Club",
		"dallasbuyersclub");
	boxOfficeIds.put("The Best Exotic Marigold Hotel",
		"bestexoticmarigoldhotel");
	boxOfficeIds.put("Brave", "brave");
	boxOfficeIds.put("Bruce Dern - Nebraska", "nebraska");
	boxOfficeIds.put("Christian Bale - American Hustle", "davido2013");
	boxOfficeIds.put("Cate Blanchett - Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("Amy Adams - American Hustle", "davido2013");
	boxOfficeIds.put("Sandra Bullock - Gravity", "gravity");
	boxOfficeIds.put("Judi Dench - Philomena", "philomena");
	boxOfficeIds.put("Meryl Streep - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("Jared Leto - Dallas Buyers Club", "dallasbuyersclub");
	boxOfficeIds.put("Michael Fassbender - 12 Years a Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Barkhad Abdi - Captain Phillips", "captainphillips");
	boxOfficeIds.put("Chiwetel Ejiofor - Twelve Years A Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Bradley Cooper - American Hustle", "davido2013");
	boxOfficeIds.put("Lupita Nyongo - 12 Years a Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Jennifer Lawrence - American Hustle", "davido2013");
	boxOfficeIds.put("June Squibb - Nebraska", "nebraska");
	boxOfficeIds.put("Julia Roberts - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("birdman", "birdman");
	boxOfficeIds.put("boyhood", "boyhood");
	boxOfficeIds.put("the imitation game", "imitationgame");
	boxOfficeIds.put("the grand budapest hotel", "grandbudapesthotel");
	boxOfficeIds.put("american sniper", "americansniper");
	boxOfficeIds.put("whiplash", "whiplash");
	boxOfficeIds.put("theory of everything", "theoryofeverything");
	boxOfficeIds.put("selma", "selma");
	boxOfficeIds.put("alejandro gonzalez inarritu - birdman", "birdman");
	boxOfficeIds.put("richard linklater - boyhood", "boyhood");
	boxOfficeIds.put("wes anderson - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("bennett miller - foxcatcher", "foxcatcher");
	boxOfficeIds.put("morten tyldum - the imitation game", "imitationgame");
	boxOfficeIds.put("eddie redmayne - theory of everything",
		"theoryofeverything");
	boxOfficeIds.put("michael keaton - birdman", "birdman");
	boxOfficeIds.put("bradley cooper - american sniper", "americansniper");
	boxOfficeIds.put("benedict cumberbatch - the imitation game",
		"imitationgame");
	boxOfficeIds.put("steve carrell - foxcatcher", "foxcatcher");
	boxOfficeIds.put("julianne moore - still alice", "stillalice");
	boxOfficeIds.put("rosamund pike - gone girl", "gonegirl");
	boxOfficeIds.put("reese witherspoon - wild", "wild2014");
	boxOfficeIds.put("felicity jones - the theory of everything",
		"theoryofeverything");
	boxOfficeIds.put("marion cotillard - two days one night",
		"twodaysonenight");
	boxOfficeIds.put("j k simmons - whiplash", "whiplash");
	boxOfficeIds.put("edward norton - birdman", "birdman");
	boxOfficeIds.put("mark ruffalo - foxcatcher", "foxcatcher");
	boxOfficeIds.put("ethan hawke - boyhood", "boyhood");
	boxOfficeIds.put("robert duvall - the judge", "judge");
	boxOfficeIds.put("Argo", "argo");
	boxOfficeIds.put("Lincoln", "lincoln");
	boxOfficeIds.put("Silver Linings Playbook", "silverliningsplaybook");
	boxOfficeIds.put("Life of Pi", "lifeofpi");
	boxOfficeIds.put("Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Django Unchained", "djangounchained");
	boxOfficeIds.put("Amour", "amour");
	boxOfficeIds.put("Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("The Master", "themaster");
	boxOfficeIds.put("Clint Eastwood - Trouble With The Curve",
		"troubewiththecurve");
	boxOfficeIds.put("Cloud Atlas", "cloudatlas");
	boxOfficeIds.put("The Dallas Buyers Club", "dallasbuyersclub");
	boxOfficeIds.put("The Dark Knight Rises", "darkknightrises");
	boxOfficeIds.put("Inside Llewyn Davis", "insidellewyndavis");
	boxOfficeIds.put("The Great Gatsby", "greatgatsby");
	boxOfficeIds.put("Flight", "flight");
	boxOfficeIds.put("Moonrise Kingdom", "moonrisekingdom");
	boxOfficeIds.put("Hitchcock", "hitchcock");
	boxOfficeIds.put("The Sessions", "sixsessions");
	boxOfficeIds.put("Skyfall", "bond23");
	boxOfficeIds.put("The Hobbit", "hobbit");
	boxOfficeIds.put("Anna Karenina", "annakarenina2012");
	boxOfficeIds.put("Hyde Park on Hudson", "hydeparkonhudson");
	boxOfficeIds.put("John Madden - The Best Exotic Marigold Hotel",
		"bestexoticmarigoldhotel");
	boxOfficeIds.put("Jonah Hill - The Wolf of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("Killing Them Softly", "killingthemsoftly");
	boxOfficeIds.put("Leonardo DiCaprio - The Wolf Of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("Leonardo Dicaprio - The Great Gatsby", "greatgatsby");
	boxOfficeIds.put("Martin Scorcese - The Wolf Of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("The Impossible", "impossible");
	boxOfficeIds.put("Ben Affleck - Argo", "argo");
	boxOfficeIds.put("Steven Spielberg - Lincoln", "lincoln");
	boxOfficeIds.put("Tom Hooper - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Ang Lee - Life Of Pi", "lifeofpi");
	boxOfficeIds.put("Paul Thomas Anderson - The Master", "themaster");
	boxOfficeIds.put("David O Russell - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Kathryn Bigelow - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Michael Haneke - Amour", "amour");
	boxOfficeIds.put("Quentin Tarantino - Django Unchained",
		"djangounchained");
	boxOfficeIds.put("The Place Beyond The Pines", "placebeyondthepines");
	boxOfficeIds.put("Benh Zeitlin - Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Joe Wright - Anna Karenina", "annakarenina2012");
	boxOfficeIds.put("Peter Jackson - The Hobbit: An Unexpected Journey",
		"hobbit");
	boxOfficeIds.put("Robert Zemeckis - Flight", "flight");
	boxOfficeIds.put("Wes Anderson - Moonrise Kingdom", "moonrisekingdom");
	boxOfficeIds.put("Ben Lewin - The Sessions", "sixsessions");
	boxOfficeIds.put("Christopher Nolan - The Dark Knight Rises",
		"darkknight");
	boxOfficeIds.put("Promised Land", "promisedland");
	boxOfficeIds.put("Quvenzhane Wallis - Beasts Of The Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Juan Antonio Bayona - The Impossible", "impossible");
	boxOfficeIds.put("Rust and Bone", "rustandbone");
	boxOfficeIds.put("Sam Mendes - Skyfall", "bond23");
	boxOfficeIds.put("Sacha Gervasi - Hitchcock", "hitchcock");
	boxOfficeIds.put("Daniel Day-Lewis - Lincoln", "lincoln");
	boxOfficeIds.put("Joaquin Phoenix - The Master", "themaster");
	boxOfficeIds.put("Denzel Washington - Flight", "flight");
	boxOfficeIds.put("John Hawkes - The Sessions", "sixsessions");
	boxOfficeIds.put("Hugh Jackman - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("The Teleportation Accident - Ned Beauman",
		"teleportationaccident");
	boxOfficeIds.put("Trouble With The Curve", "troubewiththecurve");
	boxOfficeIds.put("Bradley Cooper - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Wayne Blaire - The Sapphires", "sapphires");
	boxOfficeIds.put("Joel Edgerton - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Helen Mirren - Trumbo", "trumbo");
	boxOfficeIds.put("Helena Bonham Carter - Suffragette", "suffragette");
	boxOfficeIds.put("Rachel McAdams - Spotlight", "spotlight");
	boxOfficeIds.put("Rachel Weisz - Youth", "youth");
	boxOfficeIds.put("Amy Ryan - Bridge of Spies", "bridgeofspies");
	boxOfficeIds.put("Julianne Nicholson - Black Mass", "blackmass");
	boxOfficeIds.put("Julie Walters - Brooklyn", "brooklyn");
	boxOfficeIds.put("The Artist", "artist");
	boxOfficeIds.put("The Descendants", "descendents");
	boxOfficeIds.put("The Help", "help2011");
	boxOfficeIds.put("Hugo", "hugocabret");
	boxOfficeIds.put("War Horse", "warhorse");
	boxOfficeIds
		.put("Extremely Loud and Incredibly Close", "extremelyloud");
	boxOfficeIds.put("The Tree of Life", "treeoflife");
	boxOfficeIds.put("Midnight in Paris", "midnightinparis");
	boxOfficeIds.put("Moneyball", "moneyball");
	boxOfficeIds.put("Jean Dujardin - The Artist", "artist");
	boxOfficeIds.put("George Clooney - The Descendants", "descendents");
	boxOfficeIds.put("Brad Pitt - Moneyball", "moneyball");
	boxOfficeIds.put("Gary Oldman - Tinker Tailor Soldier Spy",
		"tinkertailorsoldierspy");
	boxOfficeIds.put("Demian Bichir - A Better Life", "abetterlife");
	boxOfficeIds.put("Octavia Spencer - The Help", "help2011");
	boxOfficeIds.put("Berenice Bejo - The Artist", "artist");
	boxOfficeIds.put("Jessica Chastain - The Help", "help2011");
	boxOfficeIds.put("Melissa McCarthy - Bridesmaids", "wiigapatow");
	boxOfficeIds.put("Janet McTeer - Albert Nobbs", "albertnobbs");
	boxOfficeIds.put("Tommy Lee Jones - Lincoln", "lincoln");
	boxOfficeIds.put("Phillip Seymour Hoffman - The Master", "themaster");
	boxOfficeIds.put("Christoph Waltz - Django Unchained",
		"djangounchained");
	boxOfficeIds.put("Robert DeNiro - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Alan Arkin - Argo", "argo");
	boxOfficeIds.put("Ang Lee - Life Of Pi", "lifeofpi");
	boxOfficeIds.put("Michael Haneke - Amour", "amour");
	boxOfficeIds.put("David O Russell - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Benh Zeitlin - Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Daniel Day-Lewis - Lincoln", "lincoln");
	boxOfficeIds.put("Hugh Jackman - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Joaquin Phoenix - The Master", "themaster");
	boxOfficeIds.put("Bradley Cooper - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Denzel Washington - Flight", "flight");
	boxOfficeIds.put("Jennifer Lawrence - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Emmanuelle Riva - Amour", "amour");
	boxOfficeIds.put("Jessica Chastain - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Naomi Watts - The Impossible", "impossible");
	boxOfficeIds.put("Quvenzhane Wallis - Beasts Of The Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Steven Spielberg - Lincoln", "lincoln");
	boxOfficeIds.put("12 Years a Slave", "twelveyearsaslave");
	boxOfficeIds.put("Gravity", "gravity");
	boxOfficeIds.put("American Hustle", "davido2013");
	boxOfficeIds.put("The Dallas Buyers Club", "dallasbuyersclub");
	boxOfficeIds.put("The Wolf Of Wall Street", "wolfofwallstreet");
	boxOfficeIds.put("Captain Phillips", "captainphillips");
	boxOfficeIds.put("Nebraska", "nebraska");
	boxOfficeIds.put("Her", "her2013");
	boxOfficeIds.put("Philomena", "philomena");
	boxOfficeIds.put("Anne Hathaway - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Sally Field - Lincoln", "lincoln");
	boxOfficeIds.put("Helen Hunt - The Sessions", "sixsessions");
	boxOfficeIds.put("Amy Adams - The Master", "themaster");
	boxOfficeIds.put("Jacki Weaver - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Sally Hawkins - Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("Alfonso Cuaron - Gravity", "gravity");
	boxOfficeIds.put("Steve McQueen - 12 Years A Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("David O Russell - American Hustle", "davido2013");
	boxOfficeIds.put("Martin Scorcese - The Wolf Of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("Alexander Payne - Nebraska", "nebraska");
	boxOfficeIds.put("Matthew McConaughey - The Dallas Buyers Club",
		"dallasbuyersclub");
	boxOfficeIds.put("Leonardo DiCaprio - The Wolf Of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("Chiwetel Ejiofor - Twelve Years A Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Bruce Dern - Nebraska", "nebraska");
	boxOfficeIds.put("Christian Bale - American Hustle", "davido2013");
	boxOfficeIds.put("Cate Blanchett - Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("Amy Adams - American Hustle", "davido2013");
	boxOfficeIds.put("Sandra Bullock - Gravity", "gravity");
	boxOfficeIds.put("Judi Dench - Philomena", "philomena");
	boxOfficeIds.put("Meryl Streep - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("Jared Leto - Dallas Buyers Club", "dallasbuyersclub");
	boxOfficeIds.put("Michael Fassbender - 12 Years a Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Barkhad Abdi - Captain Phillips", "captainphillips");
	boxOfficeIds.put("Jonah Hill - The Wolf of Wall Street",
		"wolfofwallstreet");
	boxOfficeIds.put("Bradley Cooper - American Hustle", "davido2013");
	boxOfficeIds.put("Lupita Nyongo - 12 Years a Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Jennifer Lawrence - American Hustle", "davido2013");
	boxOfficeIds.put("June Squibb - Nebraska", "nebraska");
	boxOfficeIds.put("Julia Roberts - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("birdman", "birdman");
	boxOfficeIds.put("boyhood", "boyhood");
	boxOfficeIds.put("the imitation game", "imitationgame");
	boxOfficeIds.put("the grand budapest hotel", "grandbudapesthotel");
	boxOfficeIds.put("american sniper", "americansniper");
	boxOfficeIds.put("whiplash", "whiplash");
	boxOfficeIds.put("theory of everything", "theoryofeverything");
	boxOfficeIds.put("selma", "selma");
	boxOfficeIds.put("alejandro gonzalez inarritu - birdman", "birdman");
	boxOfficeIds.put("richard linklater - boyhood", "boyhood");
	boxOfficeIds.put("wes anderson - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("bennett miller - foxcatcher", "foxcatcher");
	boxOfficeIds.put("morten tyldum - the imitation game", "imitationgame");
	boxOfficeIds.put("eddie redmayne - theory of everything",
		"theoryofeverything");
	boxOfficeIds.put("michael keaton - birdman", "birdman");
	boxOfficeIds.put("bradley cooper - american sniper", "americansniper");
	boxOfficeIds.put("benedict cumberbatch - the imitation game",
		"imitationgame");
	boxOfficeIds.put("steve carrell - foxcatcher", "foxcatcher");
	boxOfficeIds.put("julianne moore - still alice", "stillalice");
	boxOfficeIds.put("rosamund pike - gone girl", "gonegirl");
	boxOfficeIds.put("reese witherspoon - wild", "wild2014");
	boxOfficeIds.put("felicity jones - the theory of everything",
		"theoryofeverything");
	boxOfficeIds.put("marion cotillard - two days one night",
		"twodaysonenight");
	boxOfficeIds.put("j k simmons - whiplash", "whiplash");
	boxOfficeIds.put("edward norton - birdman", "birdman");
	boxOfficeIds.put("mark ruffalo - foxcatcher", "foxcatcher");
	boxOfficeIds.put("ethan hawke - boyhood", "boyhood");
	boxOfficeIds.put("robert duvall - the judge", "judge");
	boxOfficeIds.put("Argo", "argo");
	boxOfficeIds.put("Lincoln", "lincoln");
	boxOfficeIds.put("Silver Linings Playbook", "silverliningsplaybook");
	boxOfficeIds.put("Life of Pi", "lifeofpi");
	boxOfficeIds.put("Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Django Unchained", "djangounchained");
	boxOfficeIds.put("Amour", "amour");
	boxOfficeIds.put("Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("The Master", "themaster");
	boxOfficeIds.put("The Teleportation Accident - Ned Beauman",
		"nedbeauman");
	boxOfficeIds.put("The Place Beyond The Pines", "placebeyondpines");
	boxOfficeIds.put("Trouble With The Curve", "troublewithcurve");
	boxOfficeIds.put("Brave", "brave");
	boxOfficeIds.put("Inside Llewyn Davis", "insidellewyndavis");
	boxOfficeIds.put("Rust and Bone", "rustandbone");
	boxOfficeIds.put("The Great Gatsby", "greatgatsby");
	boxOfficeIds.put("Flight", "flight");
	boxOfficeIds.put("Moonrise Kingdom", "moonrisekingdom");
	boxOfficeIds.put("Promised Land", "promisedland");
	boxOfficeIds.put("The Sessions", "sixsessions");
	boxOfficeIds.put("Skyfall", "bond23");
	boxOfficeIds.put("The Avengers", "avengers11");
	boxOfficeIds.put("Anna Karenina", "annakarenina2012");
	boxOfficeIds.put("Cloud Atlas", "cloudatlas");
	boxOfficeIds.put("Hitchcock", "hitchcock");
	boxOfficeIds.put("Hyde Park on Hudson", "hydeparkonhudson");
	boxOfficeIds.put("Killing Them Softly", "killingmsoftly");
	boxOfficeIds.put("The Best Exotic Marigold Hotel",
		"bestexoticmarigoldhotel");
	boxOfficeIds.put("The Dark Knight Rises", "darkknight");
	boxOfficeIds.put("The Hobbit", "hobbit");
	boxOfficeIds.put("The Impossible", "impossible");
	boxOfficeIds.put("Ben Affleck - Argo", "argo");
	boxOfficeIds.put("Steven Spielberg - Lincoln", "lincoln");
	boxOfficeIds.put("Tom Hooper - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Ang Lee - Life Of Pi", "lifeofpi");
	boxOfficeIds.put("Paul Thomas Anderson - The Master", "themaster");
	boxOfficeIds.put("David O Russell - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Kathryn Bigelow - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Michael Haneke - Amour", "amour");
	boxOfficeIds.put("Quentin Tarantino - Django Unchained",
		"djangounchained");
	boxOfficeIds.put("Beasts Of The Southern Wild - Benh Zeitlin",
		"benhzeitlin");
	boxOfficeIds.put("Benh Zeitlin - Beasts of the Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Joe Wright - Anna Karenina", "annakarenina2012");
	boxOfficeIds.put("Peter Jackson - The Hobbit: An Unexpected Journey",
		"hobbit");
	boxOfficeIds.put("Robert Zemeckis - Flight", "flight");
	boxOfficeIds.put("Wes Anderson - Moonrise Kingdom", "moonrisekingdom");
	boxOfficeIds.put("Ben Lewin - The Sessions", "sixsessions");
	boxOfficeIds.put("Christopher Nolan - The Dark Knight Rises",
		"darkknight");
	boxOfficeIds.put("John Madden - The Best Exotic Marigold Hotel",
		"bestexoticmarigoldhotel");
	boxOfficeIds.put("Juan Antonio Bayona - The Impossible", "impossible");
	boxOfficeIds.put("Sacha Gervasi - Hitchcock", "hitchcock");
	boxOfficeIds.put("Sam Mendes - Skyfall", "bond23");
	boxOfficeIds.put("Wayne Blaire - The Sapphires", "sapphires");
	boxOfficeIds.put("Daniel Day-Lewis - Lincoln", "lincoln");
	boxOfficeIds.put("Joaquin Phoenix - The Master", "themaster");
	boxOfficeIds.put("Denzel Washington - Flight", "flight");
	boxOfficeIds.put("John Hawkes - The Sessions", "sixsessions");
	boxOfficeIds.put("Hugh Jackman - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Anthony Hopkins - Hitchcock", "hitchcock");
	boxOfficeIds.put("Leonardo Dicaprio - The Great Gatsby", "greatgatsby");
	boxOfficeIds.put("Bradley Cooper - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Clint Eastwood - Trouble With The Curve",
		"troublewithcurve");
	boxOfficeIds.put("Joel Edgerton - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Ryan Gosling - The Place Beyond The Pines",
		"placebeyondpines");
	boxOfficeIds.put("Christopher Waltz - Django Unchained",
		"djangounchained");
	boxOfficeIds.put("Jean-Louis Trintignant - Amour", "amour");
	boxOfficeIds.put("Bill Murray - Hyde Park On Hudson",
		"hydeparkonhudson");
	boxOfficeIds.put("Jamie Foxx - Django Unchained", "djangounchained");
	boxOfficeIds.put("Matt Damon - Promised Land", "promisedland");
	boxOfficeIds.put("Richard Gere - Arbitrage", "arbitrage");
	boxOfficeIds.put("Tommy Lee Jones - Hope Springs", "hopesprings");
	boxOfficeIds.put("Daniel Craig - Skyfall", "bond23");
	boxOfficeIds.put("Jake Gyllenhaal - End of Watch", "endofwatch");
	boxOfficeIds.put("Suraj Sharma - Life Of Pi", "lifeofpi");
	boxOfficeIds.put("Tom Hanks - Cloud Atlas", "cloudatlas");
	boxOfficeIds.put("Jennifer Lawrence - Silver Linings Playbook",
		"silverliningsplaybook");
	boxOfficeIds.put("Sally Field - Lincoln", "lincoln");
	boxOfficeIds.put("Anne Hathaway - Les Miserables", "lesmiserables2012");
	boxOfficeIds.put("Jessica Chastain - Zero Dark Thirty", "binladen");
	boxOfficeIds.put("Quvenzhane Wallis - Beasts Of The Southern Wild",
		"beastsofthesouthernwild");
	boxOfficeIds.put("Emannuelle Riva - Amour", "amour");
	boxOfficeIds.put("Marion Cotillard - Rust and Bone", "rustandbone");
	boxOfficeIds.put("Carey Mulligan - The Great Gatsby", "greatgatsby");
	boxOfficeIds.put("Elizabeth Olsen - Therese Raquin", "reseraquin");
	boxOfficeIds.put("Helen Mirren - Hitchcock", "hitchcock");
	boxOfficeIds.put("Judi Dench - The Best Exotic Marigold Hotel",
		"bestexoticmarigoldhotel");
	boxOfficeIds.put("Keira Knightley - Anna Karenina", "annakarenina2012");
	boxOfficeIds.put("Maggie Gyllenhaal - WonT Back Down", "wontbackdown");
	boxOfficeIds.put("Mary E Winstead - Smashed", "smashed");
	boxOfficeIds.put("Mary Elizabeth Winstead - Smashed", "smashed");
	boxOfficeIds.put("Viola Davis - Quartet", "quartet");
	boxOfficeIds.put("Maggie Smith - Quartet", "quartet");
	boxOfficeIds.put("Meryl Streep - Hope Springs", "hopesprings");
	boxOfficeIds.put("Amy Adams - Trouble With The Curve",
		"troublewithcurve");
	boxOfficeIds.put("Helen Hunt - The Sessions", "sixsessions");
	boxOfficeIds.put("Laura Linney - Hyde Park On Hudson",
		"hydeparkonhudson");
	boxOfficeIds.put("Greta Gerwig - Frances Ha", "francesha");
	boxOfficeIds.put("Saving Mr Banks", "savingmrbanks");
	boxOfficeIds.put("Mandela: Long Walk To Freedom",
		"mandela:longwalktofreedom");
	boxOfficeIds.put("Blue is the Warmest Colour", "blueiswarmestcolour");
	boxOfficeIds.put("Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("The Butler", "butler");
	boxOfficeIds.put("All Is Lost", "allislost");
	boxOfficeIds.put("Labor Day", "laborday");
	boxOfficeIds.put("The Monuments Men", "monumentsmen");
	boxOfficeIds.put("August: Osage County", "augustosagecounty");
	boxOfficeIds.put("Before Midnight", "beforemidnight");
	boxOfficeIds.put("Foxcatcher", "foxcatcher");
	boxOfficeIds.put("Fruitvale Station", "fruitvalestation");
	boxOfficeIds.put("Grace Of Monaco", "graceofmonaco");
	boxOfficeIds.put("Mud", "mud");
	boxOfficeIds.put("Rush", "rush");
	boxOfficeIds.put("The Book Thief", "bookthief");
	boxOfficeIds.put("The Secret Life of Walter Mitty",
		"secretlifeofwaltermitty");
	boxOfficeIds.put("The Third Person", "thirdperson");
	boxOfficeIds.put("Diana", "diana");
	boxOfficeIds.put("The Fifth Estate", "fifstate");
	boxOfficeIds.put("Oprah Winfrey - The Butler", "butler");
	boxOfficeIds.put("Naomie Harris - Mandela: Long Walk to Freedom",
		"mandela:longwalktofreedom");
	boxOfficeIds.put("Octavia Spencer - Fruitvale Station",
		"fruitvalestation");
	boxOfficeIds.put("Juliette Lewis - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("Sarah Paulson - 12 Years a Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Cate Blanchett - The Monuments Men", "monumentsmen");
	boxOfficeIds.put("Margo Martindale - August: Osage County",
		"augustosagecounty");
	boxOfficeIds.put("Vanessa Redgrave - Foxcatcher", "foxcatcher");
	boxOfficeIds.put("Emily Watson - The Book Thief", "bookthief");
	boxOfficeIds.put("Kristin Wiig - The Secret Life of Walter Mitty",
		"secretlifeofwaltermitty");
	boxOfficeIds.put("Alfre Woodard - 12 Years A Slave",
		"twelveyearsaslave");
	boxOfficeIds.put("Amy Adams - Her", "her2013");
	boxOfficeIds.put("Cameron Diaz - The Counselor", "counselor");
	boxOfficeIds.put("Carey Mulligan - Inside Llewyn Davis",
		"insidellewyndavis");
	boxOfficeIds.put("Lea Seydoux - Blue is The Warmest Color",
		"blueiswarmestcolor");
	boxOfficeIds
		.put("Melanie Diaz - Fruitvale Station", "fruitvalestation");
	boxOfficeIds
		.put("Vanessa Redgrave - Unfinished Song", "unfinishedsong");
	boxOfficeIds.put("Emma Thompson - Saving Mr Banks", "savingmrbanks");
	boxOfficeIds.put("Rooney Mara - Aint Them Bodies Saints",
		"aintmbodiessaints");
	boxOfficeIds.put("Elizabeth Olsen - Oldboy", "oldboy");
	boxOfficeIds.put("Adele Exarchopuolos - Blue Is The Warmest Colour",
		"blueiswarmestcolour");
	boxOfficeIds.put("Jennifer Lawrence - Serena", "serena");
	boxOfficeIds.put("Marion Cotillard - The Immigrant", "immigrant");
	boxOfficeIds.put("Naomi Watts - Diana", "diana");
	boxOfficeIds.put("Berenice Bejo - The Past", "past");
	boxOfficeIds.put("Kate Winslet - Labor Day", "laborday");
	boxOfficeIds.put("Nicole Kidman - Grace Of Monaco", "graceofmonaco");
	boxOfficeIds.put("Brie Larson - Short Term 12", "shortterm12");
	boxOfficeIds.put("Julie Delpy - Before Midnight", "beforemidnight");
	boxOfficeIds.put("Julia Louis-Dreyfus - Enough Said", "enoughsaid");
	boxOfficeIds.put("Out Of The Furnace", "outoffurnace");
	boxOfficeIds.put("The Counselor", "counselor");
	boxOfficeIds.put("The Bling Ring", "blingring");
	boxOfficeIds.put("The Monument Man", "monumentman");
	boxOfficeIds.put("August : Orange County", "augustosagecounty");
	boxOfficeIds.put("Joel and Ethan Coen - Inside Llewyn Davis",
		"insidellewyndavis");
	boxOfficeIds.put("Paul Greengrass - Captain Phillips",
		"captainphillips");
	boxOfficeIds.put("Ron Howard - Rush", "rush");
	boxOfficeIds.put("Spike Jonze - Her", "her2013");
	boxOfficeIds.put("George Clooney - The Monuments Men", "monumentsmen");
	boxOfficeIds.put("Woody Allen - Blue Jasmine", "bluejasmine");
	boxOfficeIds.put("J C Chandor - All is Lost", "allislost");
	boxOfficeIds.put("Jason Reitman - Labor Day", "laborday");
	boxOfficeIds.put("John Lee Hancock - Saving Mr Banks", "savingmrbanks");
	boxOfficeIds.put("John Wells - August: Orange County",
		"augustosagecounty");
	boxOfficeIds.put("Lee Daniels - The Butler", "butler");
	boxOfficeIds.put("Ridley Scott - The Counselor", "counselor");
	boxOfficeIds
		.put("Ryan Coogler - Fruitvale Station", "fruitvalestation");
	boxOfficeIds.put("Robert Redford - All Is Lost", "allislost");
	boxOfficeIds.put("Tom Hanks - Captain Phillips", "captainphillips");
	boxOfficeIds.put("Idris Elba - Mandela: Long Walk To Freedom",
		"mandela:longwalktofreedom");
	boxOfficeIds.put("Forest Whitaker - The Butler", "butler");
	boxOfficeIds.put("Tom Hanks - Saving Mr Banks", "savingmrbanks");
	boxOfficeIds.put("George Clooney - Gravity", "gravity");
	boxOfficeIds.put("Joaquin Phoenix - The Immigrant", "immigrant");
	boxOfficeIds.put("Michael Fassbender - The Counselor", "counselor");
	boxOfficeIds
		.put("Miles Teller - The Spectacular Now", "spectacularnow");
	boxOfficeIds.put("Steve Coogan - Philomena", "philomena");
	boxOfficeIds.put("Ethan Hawke - Before Midnight", "beforemidnight");
	boxOfficeIds.put("Benedict Cumberbatch - The Fifth Estate", "fifstate");
	boxOfficeIds.put("Joaquin Phoenix - Her", "her2013");
	boxOfficeIds.put("Michael B Jordan - Fruitvale", "fruitvale");
	boxOfficeIds.put("Oscar Isaac - Inside Llewyn Davis",
		"insidellewyndavis");
	boxOfficeIds.put("Steve Carell - Foxcatcher", "foxcatcher");
	boxOfficeIds.put("Viola Davis - The Help", "help2011");
	boxOfficeIds.put("Meryl Streep - The Iron Lady", "ironlady");
	boxOfficeIds.put("Michelle Williams - My Week With Marilyn",
		"myweekwithmarilyn");
	boxOfficeIds.put("Glenn Close - Albert Nobbs", "albertnobbs");
	boxOfficeIds.put("Rooney Mara - The Girl With the Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("Christopher Plummer - Beginners", "beginners");
	boxOfficeIds.put("Kenneth Branagh - My Week With Marilyn",
		"myweekwithmarilyn");
	boxOfficeIds.put("Max Von Sydow - Extremely Loud and Incredibly Close",
		"extremelyloud");
	boxOfficeIds.put("Nick Nolte - Warrior", "warrior10");
	boxOfficeIds.put("Jonah Hill - Moneyball", "moneyball");
	boxOfficeIds.put("patricia arquette - boyhood", "boyhood");
	boxOfficeIds.put("emma stone - birdman", "birdman");
	boxOfficeIds.put("meryl streep - into the woods", "intothewoods");
	boxOfficeIds.put("keira knightley - the imitation game",
		"imitationgame");
	boxOfficeIds.put("laura dern - wild", "wild2014");
	boxOfficeIds.put("laura dern - the fault in our stars",
		"faultinourstars");
	boxOfficeIds.put("The Kings Speech", "kingsspeech");
	boxOfficeIds.put("The Social Network", "socialnetwork");
	boxOfficeIds.put("True Grit", "truegrit2010");
	boxOfficeIds.put("Black Swan", "blackswan");
	boxOfficeIds.put("The Fighter", "fighter10");
	boxOfficeIds.put("Inception", "inception");
	boxOfficeIds.put("127 Hours", "127hours");
	boxOfficeIds.put("Toy Story 3", "toystory3");
	boxOfficeIds.put("Winters Bone", "wintersbone");
	boxOfficeIds.put("The Kids Are Alright", "kidsareallright");
	boxOfficeIds.put("David Fincher - The Social Network", "socialnetwork");
	boxOfficeIds.put("Tom Hooper - The Kings Speech", "kingsspeech");
	boxOfficeIds.put("Darren Aronofsky - Black Swan", "blackswan");
	boxOfficeIds.put("Joel &amp; Ethan Coen - True Grit", "truegrit2010");
	boxOfficeIds.put("David O Russell - The Fighter", "fighter10");
	boxOfficeIds.put("Colin Firth - The Kings Speech", "kingsspeech");
	boxOfficeIds.put("James Franco - 127 Hours", "127hours");
	boxOfficeIds.put("Jesse Eisenberg - The Social Network",
		"socialnetwork");
	boxOfficeIds.put("Jeff Bridges - True Grit", "truegrit2010");
	boxOfficeIds.put("Javier Bardem - Biutiful", "biutiful");
	boxOfficeIds.put("Natalie Portman - Black Swan", "blackswan");
	boxOfficeIds.put("Annette Bening - The Kids Are All Right",
		"kidsareallright");
	boxOfficeIds.put("Jennifer Lawrence - Winters Bone", "wintersbone");
	boxOfficeIds.put("Nicole Kidman - Rabbit Hole", "rabbithole");
	boxOfficeIds.put("Michelle Williams - Blue Valentine", "bluevalentine");
	boxOfficeIds.put("Christian Bale - The Fighter", "fighter10");
	boxOfficeIds.put("Geoffery Rush - The Kings Speech", "kingsspeech");
	boxOfficeIds.put("Mark Ruffalo - The Kids Are All Right",
		"kidsareallright");
	boxOfficeIds.put("John Hawkes - Winters Bone", "wintersbone");
	boxOfficeIds.put("Jeremy Renner - The Town", "town10");
	boxOfficeIds.put("Melissa Leo - The Fighter", "fighter10");
	boxOfficeIds.put("Hailee Steinfeld - True Grit", "truegrit2010");
	boxOfficeIds.put("Helena Bonham Carter - The Kings Speech",
		"kingsspeech");
	boxOfficeIds.put("Amy Adams - The Fighter", "fighter10");
	boxOfficeIds.put("Jacki Weaver - Animal Kingdom", "animalkingdom");
	boxOfficeIds.put("Michel Hazanavicius - The Artist", "artist");
	boxOfficeIds.put("Martin Scorsese - Hugo", "hugocabret");
	boxOfficeIds.put("Alexander Payne - The Descendants", "descendents");
	boxOfficeIds.put("Woody Allen - Midnight in Paris", "midnightinparis");
	boxOfficeIds.put("Terrance Malik - The Tree of Life", "treeoflife");
	boxOfficeIds.put("Gus Van Sant", "gusvansant");
	boxOfficeIds.put("Bennett Miller - Foxcatcher", "foxcatcher");
	boxOfficeIds.put("Leonardo DiCaprio - The Great Gatsby", "greatgatsby");

	boxOfficeIds.put("45 Years", "45years");
	boxOfficeIds.put("A Bigger Splash", "abiggersplash");
	boxOfficeIds.put("Abraham Attah - Beasts of No Nation",
		"beastsofnonation");
	boxOfficeIds.put("Adam Jones", "adamjones");
	boxOfficeIds.put("Alejandro Gonzalez Inarritu - The Revenant",
		"revenant");
	boxOfficeIds.put("Alicia Vikander - The Danish Girl", "danishgirl");
	boxOfficeIds.put("Amy", "amy");
	boxOfficeIds.put("Amy Schumer - Trainwreck", "trainwreck");
	boxOfficeIds.put("Andrew Garfield - 99 Homes", "99homes");
	boxOfficeIds.put("Angelina Jolie - By the Sea", "bysea");
	boxOfficeIds.put("Beasts of No Nation", "beastsofnonation");
	boxOfficeIds.put("Benicio Del Toro - Sicario", "sicario");
	boxOfficeIds.put("Black Mass", "blackmass");
	boxOfficeIds.put("Blythe Danner - Ill See You in my Dreams",
		"illseeyouinmydreams");
	boxOfficeIds.put("Brad Pitt - By the Sea", "bysea");
	boxOfficeIds.put("Bradley Cooper - Adam Jones", "adamjones");
	boxOfficeIds.put("Bradley Cooper - BUrnt", "burnt");
	boxOfficeIds.put("Bradley Cooper - Joy", "joy");
	boxOfficeIds.put("Bridge of Spies", "bridgeofspies");
	boxOfficeIds.put("Brie Larson - Room", "room");
	boxOfficeIds.put("Brooklyn", "brooklyn");
	boxOfficeIds.put("Bryan Cranston - Trumbo", "trumbo");
	boxOfficeIds.put("By The Sea", "bysea");
	boxOfficeIds.put("Carey Mulligan - Suffragette", "suffragette");
	boxOfficeIds.put("Carol", "carol");
	boxOfficeIds.put("Cary Fukunage - Beasts of No Nation",
		"beastsofnonation");
	boxOfficeIds.put("Cary Joji Fukunaga - Beasts of No Nation",
		"beastsofnonation");
	boxOfficeIds.put("Cate Blanchett - Carol", "carol");
	boxOfficeIds.put("Cate Blanchett - Truth", "truth");
	boxOfficeIds.put("Charlize Theron - Mad Max: Fury Road",
		"madmax:furyroad");
	boxOfficeIds.put("Charlotte Rampling - 45 Years", "45years");
	boxOfficeIds.put("Chris Hemsworth - In the Heart of the Sea",
		"inheartofsea");
	boxOfficeIds.put("Christian Bale - The Big Short", "bigshort");
	boxOfficeIds.put("Christophe Waltz - Spectre", "spectre");
	boxOfficeIds.put("Concussion", "concussion");
	boxOfficeIds.put("Creed", "creed");
	boxOfficeIds.put("Daniel Craig - Spectre", "spectre");
	boxOfficeIds.put("Danny Boyle - Steve Jobs", "stevejobs");
	boxOfficeIds.put("David O Russell - Joy", "joy");
	boxOfficeIds.put("Demolition", "demolition");
	boxOfficeIds.put("Diane Ladd - Joy", "joy");
	boxOfficeIds.put("Don Cheadle - Miles Ahead", "milesahead");
	boxOfficeIds.put("Eddie Redmayne - The Danish Girl", "danishgirl");
	boxOfficeIds.put("Edgar Ramirez - Joy", "joy");
	boxOfficeIds.put("Elizabeth Banks - Love And Mercy", "loveandmercy");
	boxOfficeIds.put("Ellen Page - Freeheld", "freeheld");
	boxOfficeIds.put("Emily Blunt - Sicario", "sicario");
	boxOfficeIds.put("Everest", "everest");
	boxOfficeIds.put("Ex Machina", "exmachina");
	boxOfficeIds.put("Far From The Madding Crowd", "farfrommaddingcrowd");
	boxOfficeIds.put("Freeheld", "freeheld");
	boxOfficeIds.put("Genius", "genius");
	boxOfficeIds.put("George Miller - Mad Max Fury Road", "madmaxfuryroad");
	boxOfficeIds.put("Geza Rohrig - Son of Saul", "sonofsaul");
	boxOfficeIds.put("Harvey Keitel - Youth", "youth");
	boxOfficeIds.put("Helen Mirren - Woman in Gold", "womaningold");
	boxOfficeIds.put("I Saw the Light", "isawlight");
	boxOfficeIds.put("Ian McKellen - Mr Holmes", "mrholmes");
	boxOfficeIds.put("Idris Elba - Beast of No Nation", "beastofnonation");
	boxOfficeIds.put("In The Heart of the Sea", "inheartofsea");
	boxOfficeIds.put("Inside Out", "insideout");
	boxOfficeIds.put("J J Abrams - Star Wars:The Force Awakens",
		"starwars:forceawakens");
	boxOfficeIds.put("Jacob Tremblay - Room", "room");
	boxOfficeIds.put("Jake Gyllenhaal - Demolition", "demolition");
	boxOfficeIds.put("Jake Gyllenhaal - Southpaw", "southpaw");
	boxOfficeIds.put("Jane Fonda - Youth", "youth");
	boxOfficeIds.put("Jason Mitchell - Straight Outta Compton",
		"straightouttacompton");
	boxOfficeIds.put("Jason Segel - The End of the Tour", "endoftour");
	boxOfficeIds.put("Jason Statham - Spy", "spy");
	boxOfficeIds.put("Jeff Daniels - Steve Jobs", "stevejobs");
	boxOfficeIds.put("Jennifer Jason Leigh - The Hateful Eight",
		"hatefuleight");
	boxOfficeIds.put("Jennifer Lawrence - Joy", "joy");
	boxOfficeIds.put("JJ Abrams - Star Wars", "starwars");
	boxOfficeIds.put("Joan Allen - Room", "room");
	boxOfficeIds.put("Joel Edgerton - Black Mass", "blackmass");
	boxOfficeIds.put("John Crowley - Brooklyn", "brooklyn");
	boxOfficeIds.put("John Cusack - Love and Mercy", "loveandmercy");
	boxOfficeIds.put("Johnny Depp - Black Mass", "blackmass");
	boxOfficeIds.put("Joseph Gordon-Levitt - Snowden", " Snowden");
	boxOfficeIds.put("Joseph Gordon-Levitt - The Walk", " The Walk");
	boxOfficeIds.put("Joy", "joy");
	boxOfficeIds.put("Julia Roberts - Secret In Their Eyes",
		"secretinireyes");
	boxOfficeIds.put("Julia Roberts - The Secret In Their Eyes",
		"secretinireyes");
	boxOfficeIds.put("Julianne Moore - Freeheld", "freeheld");
	boxOfficeIds.put("Juliette Binoche - Clouds Of Sils Maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("Kate Winslet - Steve Jobs", "stevejobs");
	boxOfficeIds.put("Kristen Stewart - Clouds of Sila Maria",
		"cloudsofsilamaria");
	boxOfficeIds.put("Kristen Stewart - Clouds of Sils Maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("Kurt Russell - The Hateful Eight", "hatefuleight");
	boxOfficeIds.put("Legend", "legend");
	boxOfficeIds.put("Lenny Abrahamson - Room", "room");
	boxOfficeIds.put("Leonardo DiCaprio - The Revenant", "revenant");
	boxOfficeIds.put("Lily Tomlin - Grandma", "grandma");
	boxOfficeIds.put("Love and Mercy", "loveandmercy");
	boxOfficeIds.put("Macbeth", "macbeth");
	boxOfficeIds.put("Mad Max: Fury Road", "madmax:furyroad");
	boxOfficeIds.put("Mad Max:Fury Road", "madmax:furyroad");
	boxOfficeIds.put("Maggie Smith - The Lady in the Van", "ladyinvan");
	boxOfficeIds.put("Marion Cotillard - Macbeth", "macbeth");
	boxOfficeIds.put("Mark Ruffalo - Spotlight", "spotlight");
	boxOfficeIds.put("Mark Rylance - Bridge of Spies", "bridgeofspies");
	boxOfficeIds.put("Matt Damon - The Martian", "martian");
	boxOfficeIds.put("Matt Damon - Trumbo", "trumbo");
	boxOfficeIds.put("Me and Earl and the Dying Girl",
		"meandearlanddyinggirl");
	boxOfficeIds.put("Meryl Streep - Ricki and the Flash", "rickiandflash");
	boxOfficeIds.put("Michael Caine - Youth", "youth");
	boxOfficeIds.put("Michael Fassbender - Macbeth", "macbeth");
	boxOfficeIds.put("Michael Fassbender - Steve Jobs", "stevejobs");
	boxOfficeIds.put("Michael Fassbender - The Light Between Oceans",
		"lightbetweenoceans");
	boxOfficeIds.put("Michael Keaton - Spotlight", "spotlight");
	boxOfficeIds.put("Michael Shannon - 99 Homes", "99homes");
	boxOfficeIds.put("Money Monster", "moneymonster");
	boxOfficeIds.put("Mr Holmes", "mrholmes");
	boxOfficeIds.put("Naomi Watts - Demolition", "demolition");
	boxOfficeIds.put("Nicole Kidman - Secret in Their Eyes",
		"secretinireyes");
	boxOfficeIds.put("Olivia Wilde - Meadowland", "meadowland");
	boxOfficeIds.put("Our Brand is Crisis", "ourbrandiscrisis");
	boxOfficeIds.put("Paolo Sorrentino - Youth", "youth");
	boxOfficeIds.put("Paul Dano - Love and Mercy", "loveandmercy");
	boxOfficeIds
		.put("Quentin Taratino - The Hateful Eight", "hatefuleight");
	boxOfficeIds.put("Richard Gere - Time out of Mind", "timeoutofmind");
	boxOfficeIds.put("Ridley Scott - The Martian", "martian");
	boxOfficeIds.put("Robert De Niro - Joy", "joy");
	boxOfficeIds.put("Robert Redford - Truth", "truth");
	boxOfficeIds.put("Room", "room");
	boxOfficeIds.put("Rooney Mara - Carol", "carol");
	boxOfficeIds
		.put("Samuel L Jackson - The Hateful Eight", "hatefuleight");
	boxOfficeIds.put("Sandra Bullock - Our Brand is Crisis",
		"ourbrandiscrisis");
	boxOfficeIds.put("Saoirse Ronan - Brooklyn", "brooklyn");
	boxOfficeIds.put("Sarah Gavron - Suffragette", "suffragette");
	boxOfficeIds.put("Sarah Silverman - I Smile Back", "ismileback");
	boxOfficeIds.put("Scott Cooper - Black Mass", "blackmass");
	boxOfficeIds.put("Seth Rogen - Steve Jobs", "stevejobs");
	boxOfficeIds.put("Sicario", "sicario");
	boxOfficeIds.put("Snowden", "snowden");
	boxOfficeIds.put("Son of Saul", "sonofsaul");
	boxOfficeIds.put("Southpaw", "southpaw");
	boxOfficeIds.put("Spectre", "spectre");
	boxOfficeIds.put("Spotlight", "spotlight");
	boxOfficeIds.put("Star Wars: The Force Awakens",
		"starwars:forceawakens");
	boxOfficeIds
		.put("Star Wars:The Force Awakens", "starwars:forceawakens");
	boxOfficeIds.put("Steve Jobs", "stevejobs");
	boxOfficeIds.put("Steven Spielberg - Bridge of Spies", "bridgeofspies");
	boxOfficeIds.put("Straight Outta Compton", "straightouttacompton");
	boxOfficeIds.put("Suffragette", "suffragette");
	boxOfficeIds.put("Susan Sarandon - The Meddler", "meddler");
	boxOfficeIds.put("The BIg Short", "bigshort");
	boxOfficeIds.put("The Danish Girl", "danishgirl");
	boxOfficeIds.put("The Dressmaker", "dressmaker");
	boxOfficeIds.put("The End of the Tour", "endoftour");
	boxOfficeIds.put("The Good Dinosaur", "gooddinosaur");
	boxOfficeIds.put("The Hateful Eight", "hatefuleight");
	boxOfficeIds.put("The Hunger Games: Mockingjay - Part 2",
		"hungergames:mockingjaypart2");
	boxOfficeIds.put("The Hunger Games: Mockingjay Part 2",
		"hungergames:mockingjaypart2");
	boxOfficeIds.put("The Martian", "martian");
	boxOfficeIds.put("The Program", "program");
	boxOfficeIds.put("The Revenant", "revenant");
	boxOfficeIds.put("The Walk", "walk");
	boxOfficeIds.put("The Whole Truth", "wholetruth");
	boxOfficeIds.put("Thomas McCarthy - Spotlight", "spotlight");
	boxOfficeIds.put("Todd Haynes - Carol", "carol");
	boxOfficeIds.put("Tom Courtenay - 45 Years", "45years");
	boxOfficeIds.put("Tom Courtney - 45 Years", "45years");
	boxOfficeIds.put("Tom Hanks - A Hologram For The King",
		"ahologramforking");
	boxOfficeIds.put("Tom Hanks - Bridge of Spies", "bridgeofspies");
	boxOfficeIds.put("Tom Hardy - Legend", "legend");
	boxOfficeIds.put("Tom Hardy - The Revenant", "revenant");
	boxOfficeIds.put("Tom Hiddleston - I Saw the Light", "isawlight");
	boxOfficeIds.put("Tom Hooper - The Danish Girl", "danishgirl");
	boxOfficeIds.put("Topher Grace - Truth", "truth");
	boxOfficeIds.put("Trumbo", "trumbo");
	boxOfficeIds.put("Truth", "truth");
	boxOfficeIds.put("Tulip Fever", "tulipfever");
	boxOfficeIds.put("United Passions", "unitedpassions");
	boxOfficeIds.put("Viola Davis - Lila and Eve", "lilaandeve");
	boxOfficeIds.put("Will Smith - Concussion", "concussion");
	boxOfficeIds.put("Youth", "youth");
	// second crawl

	boxOfficeIds.put("Abigail Breslin - Little Miss Sunshine",
		"littlemisssunshine");
	boxOfficeIds.put("Adriana Barraza - Babel", "babel");
	boxOfficeIds.put("Alan Arkin - Little Miss Sunshine",
		"littlemisssunshine");
	boxOfficeIds.put("Alejandro Gonzales Inarritu",
		"alejandrogonzalesinarritu");
	boxOfficeIds.put("Babel", "babel");
	boxOfficeIds.put("Cate Blanchett - Notes on a Scandal",
		"notesonascandal");
	boxOfficeIds.put("Clint Eastwood - Letters from Iwo Jima",
		"lettersfromiwojima");
	boxOfficeIds.put("Djimon Hounsou - Blood Diamond", "blooddiamond");
	boxOfficeIds.put("Eddie Murphy - Dreangirls", "dreamgirls");
	boxOfficeIds.put("Forest Whittaker - The Last King of Scotland",
		"lastkingofscotland");
	boxOfficeIds.put("Helen Mirren - The Queen", "queen");
	boxOfficeIds.put("Jackie Earle Haley - Little Children",
		"littlechildren");
	boxOfficeIds.put("Jennifer Hudson - Dreamgirls", "dreamgirls");
	boxOfficeIds.put("Judi Dench - Notes on a Scandal", "notesonascandal");
	boxOfficeIds.put("Kate Winslett - Little Children", "littlechildren");
	boxOfficeIds.put("Leonardo DiCaprio - Blood Diamond", "blooddiamond");
	boxOfficeIds.put("Letters From Iwo Jima", "lettersfromiwojima");
	boxOfficeIds.put("Little Miss Sunshine", "littlemisssunshine");
	boxOfficeIds.put("Mark Wahlberg - The Departed", "departed");
	boxOfficeIds.put("Martin Scorsese - The Departed", "departed");
	boxOfficeIds.put("Meryl Streep - The Devil Wears Prada",
		"devilwearsprada");
	boxOfficeIds.put("Paul Greengrass - United 93", "united93");
	boxOfficeIds.put("Penelope Cruz - Volver", "volver");
	boxOfficeIds.put("Peter O Toole - Venus", "venus");
	boxOfficeIds.put("Rinko Kikucki - Babel", "babel");
	boxOfficeIds.put("Ryan Gosling - Half Nelson", "halfnelson");
	boxOfficeIds.put("Stephen Frears - The Queen", "queen");
	boxOfficeIds.put("The Departed", "departed");
	boxOfficeIds.put("The Queen", "queen");
	boxOfficeIds.put("Will Smith - Pursuit of Happiness",
		"pursuitofhappyness");
	boxOfficeIds.put("Atonement", "atonement");
	boxOfficeIds.put("Cate Blanchett", "cateblanchett");
	boxOfficeIds.put("Daniel Day-Lewis", "danielday-lewis");
	boxOfficeIds.put("Ellen Page", "ellenpage");
	boxOfficeIds.put("George Clooney", "georgeclooney");
	boxOfficeIds.put("Johnny Depp", "johnnydepp");
	boxOfficeIds.put("Julie Christie", "juliechristie");
	boxOfficeIds.put("Juno", "juno");
	boxOfficeIds.put("Laura Linney", "lauralinney");
	boxOfficeIds.put("Marion Cotillard", "marioncotillard");
	boxOfficeIds.put("Michael Clayton", "michaelclayton");
	boxOfficeIds.put("No Country For Old Men", "nocountryforoldmen");
	boxOfficeIds.put("There Will Be Blood", "rewillbeblood");
	boxOfficeIds.put("Tommy Lee Jones", "tommyleejones");
	boxOfficeIds.put("Viggo Mortensen", "viggomortensen");
	boxOfficeIds.put("Amy Adams", "amyadams");
	boxOfficeIds.put("Angelina Jolie", "angelinajolie");
	boxOfficeIds.put("Anne Hathaway", "annehathaway");
	boxOfficeIds.put("Any Other", "anyor");
	boxOfficeIds.put("Ben Whishaw", "benwhishaw");
	boxOfficeIds.put("Brad Pitt - The Curious Case Of Benjamin Button",
		"curiouscaseofbenjaminbutton");
	boxOfficeIds.put("Colin Firth", "colinfirth");
	boxOfficeIds.put("Daniel Day-Lewis", "danielday-lewis");
	boxOfficeIds.put("Danny Boyle", "dannyboyle");
	boxOfficeIds.put("David Fincher", "davidfincher");
	boxOfficeIds.put("Frank Langella - Frost/Nixon", "frostnixon");
	boxOfficeIds.put("George Clooney", "georgeclooney");
	boxOfficeIds.put("Hal Holbrook", "halholbrook");
	boxOfficeIds.put("Jeff Bridges", "jeffbridges");
	boxOfficeIds.put("Jeremy Renner", "jeremyrenner");
	boxOfficeIds.put("Kate Winslet", "katewinslet");
	boxOfficeIds.put("Marisa Tomei", "marisatomei");
	boxOfficeIds.put("Matt Damon", "mattdamon");
	boxOfficeIds.put("Melissa Leo", "melissaleo");
	boxOfficeIds.put("Meryl Streep", "merylstreep");
	boxOfficeIds.put("Michael Stuhlbarg", "michaelstuhlbarg");
	boxOfficeIds.put("Mickey Rourke - The Wrestler", "wrestler");
	boxOfficeIds.put("Morgan Freeman", "morganfreeman");
	boxOfficeIds.put("Penelope Cruz", "penelopecruz");
	boxOfficeIds.put("Richard Jenkins", "richardjenkins");
	boxOfficeIds.put("Ron Howard", "ronhoward");
	boxOfficeIds.put("Sean Penn - Milk", "milk");
	boxOfficeIds.put("Stephen Daldry", "stephendaldry");
	boxOfficeIds.put("Taraji P Henson", "tarajiphenson");
	boxOfficeIds.put("Viggo Mortensen", "viggomortensen");
	boxOfficeIds.put("Viola Davis", "violadavis");
	boxOfficeIds.put("A Serious Man", "aseriousman");
	boxOfficeIds.put("A Single Man", "asingleman");
	boxOfficeIds.put("Aaron Schneider", "aaronschneider");
	boxOfficeIds.put("Abbie Cornish", "abbiecornish");
	boxOfficeIds.put("Adjustment Bureau", "adjustmentbureau");
	boxOfficeIds.put("Amelia", "amelia");
	boxOfficeIds.put("Amy Adams", "amyadams");
	boxOfficeIds.put("An Education", "aneducation");
	boxOfficeIds.put("Andrew Garfield", "andrewgarfield");
	boxOfficeIds.put("Andrew Garfield - Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Andrew Garfield - The Social Network",
		"socialnetwork");
	boxOfficeIds.put("Anna Kendrick", "annakendrick");
	boxOfficeIds.put("Anne Hathaway", "annehathaway");
	boxOfficeIds.put("Annette Bening", "annettebening");
	boxOfficeIds.put("Another Year", "anotheryear");
	boxOfficeIds.put("Avatar", "avatar");
	boxOfficeIds.put("Barbara Hershey - Black Swan", "blackswan");
	boxOfficeIds.put("Ben Affleck - The Town", "town10");
	boxOfficeIds.put("Bill Murray - Get Low", "getlow");
	boxOfficeIds.put("Biutiful", "biutiful");
	boxOfficeIds.put("Blue Valentine", "bluevalentine");
	boxOfficeIds.put("Bob Hoskins - Made in Dagenham", "madeindagenham");
	boxOfficeIds.put("Bright Star", "brightstar");
	boxOfficeIds.put("Bryce Dallas Howard - Hereafter", "hereafter");
	boxOfficeIds.put("Carey Mulligan", "careymulligan");
	boxOfficeIds.put("Christoph Waltz", "christophwaltz");
	boxOfficeIds.put("Christopher Nolan", "christophernolan");
	boxOfficeIds.put("Christopher Nolan - Inception", "inception");
	boxOfficeIds.put("Christopher Plummer", "christopherplummer");
	boxOfficeIds.put("Clint Eastwood", "clinteastwood");
	boxOfficeIds.put("Clint Eastwood - Hereafter", "hereafter");
	boxOfficeIds.put("Coen Brothers - True Grit", "truegrit2010");
	boxOfficeIds.put("Colin Firth", "colinfirth");
	boxOfficeIds.put("Conviction", "conviction");
	boxOfficeIds.put("Dale Dickey", "daledickey");
	boxOfficeIds.put("Danny Boyle", "dannyboyle");
	boxOfficeIds.put("Danny Boyle - 127 Hours", "127hours");
	boxOfficeIds.put("Darren Aronofsky", "darrenaronofsky");
	boxOfficeIds.put("David Fincher", "davidfincher");
	boxOfficeIds.put("David O Russell", "davidorussell");
	boxOfficeIds.put("Debra Granik", "debragranik");
	boxOfficeIds.put("Debra Granik - Winters Bone", "wintersbone");
	boxOfficeIds.put("Derek Cianfrance", "derekcianfrance");
	boxOfficeIds.put("Diane Keaton - Morning Glory", "morningglory");
	boxOfficeIds.put("Diane Lane", "dianelane");
	boxOfficeIds.put("Dianne Wiest", "diannewiest");
	boxOfficeIds.put("District 9", "district9");
	boxOfficeIds.put("Doug Linman - Fair Game", "fairgame");
	boxOfficeIds.put("Due Date", "duedate");
	boxOfficeIds.put("Ed Harris - The Way Back", "wayback");
	boxOfficeIds.put("Edward Zwick - Love and Other Things",
		"loveandorthings");
	boxOfficeIds.put("Elle Fanning", "ellefanning");
	boxOfficeIds.put("Ethan and Joel Coen", "ethanandjoelcoen");
	boxOfficeIds.put("Fair Game", "fairgame");
	boxOfficeIds.put("Fantastic Mr Fox", "fantasticmrfox");
	boxOfficeIds.put("Gabby Sidibe", "gabbysidibe");
	boxOfficeIds.put("George Clooney", "georgeclooney");
	boxOfficeIds.put("Get Low", "getlow");
	boxOfficeIds.put("Hailee Steinfeld", "haileesteinfeld");
	boxOfficeIds.put("Helen Mirren", "helenmirren");
	boxOfficeIds.put("Hereafter", "hereafter");
	boxOfficeIds.put("Hilary Swank", "hilaryswank");
	boxOfficeIds.put("How to Train Your Dragon", "howtotrainyourdragon");
	boxOfficeIds.put("Howl", "howl");
	boxOfficeIds.put("Inglorious Basterds", "ingloriousbasterds");
	boxOfficeIds.put("Invictus", "eastwood09");
	boxOfficeIds.put("Jack Goes Boating", "jackgoesboating");
	boxOfficeIds.put("Jake Gyllenhaal", "jakegyllenhaal");
	boxOfficeIds.put("James Cameron", "jamescameron");
	boxOfficeIds.put("Jason Reitman", "jasonreitman");
	boxOfficeIds.put("Jeff Bridges", "jeffbridges");
	boxOfficeIds.put("Jennifer Lawrence", "jenniferlawrence");
	boxOfficeIds.put("Jeremy Renner", "jeremyrenner");
	boxOfficeIds.put("Jim Broadbent - Another Year", "anotheryear");
	boxOfficeIds.put("Jim Sturgess - The Way Back", "wayback");
	boxOfficeIds.put("Joel &amp; Ethan Coen", "joel&amp;ethancoen");
	boxOfficeIds.put("John Cameron Mitchell", "johncameronmitchell");
	boxOfficeIds.put("Josh Brolin - True Grit", "truegrit2010");
	boxOfficeIds.put("Judi Dench", "judidench");
	boxOfficeIds.put("Julia Roberts", "juliaroberts");
	boxOfficeIds.put("Julianne Moore", "juliannemoore");
	boxOfficeIds.put("Justin Timberlake - The Social Network",
		"socialnetwork");
	boxOfficeIds.put("Kathryn Bigelow", "kathrynbigelow");
	boxOfficeIds.put("Keira Knightley - Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Kristin Scott Thomas - Nowhere Boy", "nowhereboy");
	boxOfficeIds.put("Lee Daniels", "leedaniels");
	boxOfficeIds.put("Lee Unkrich - Toy Story 3", "toystory3");
	boxOfficeIds.put("Leonardo DiCaprio - Inception", "inception");
	boxOfficeIds.put("Lesley Manville", "lesleymanville");
	boxOfficeIds.put("Lisa Cholodenko", "lisacholodenko");
	boxOfficeIds.put("Lisa Cholodenko - The Kids Are All Right",
		"kidsareallright");
	boxOfficeIds.put("London Boulevard", "londonboulevard");
	boxOfficeIds.put("Lone Scherfig", "lonescherfig");
	boxOfficeIds.put("Love and Other Drugs", "loveandordrugs");
	boxOfficeIds.put("Made in Dagenham", "madeindagenham");
	boxOfficeIds.put("Maggie Gyllenhaal", "maggiegyllenhaal");
	boxOfficeIds.put("Marion Cotillard", "marioncotillard");
	boxOfficeIds.put("Marion Cotillard - Inception", "inception");
	boxOfficeIds.put("Mark Romanek", "markromanek");
	boxOfficeIds.put("Mark Romanek - Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Mark Wahlberg - The Fighter", "fighter10");
	boxOfficeIds.put("Martin Scorcese", "martinscorcese");
	boxOfficeIds.put("Martin Scorsese - Shutter Island", "shutterisland");
	boxOfficeIds.put("Matt Damon", "mattdamon");
	boxOfficeIds.put("Matt Damon - Hereafter", "hereafter");
	boxOfficeIds.put("Matt Damon - True Grit", "truegrit2010");
	boxOfficeIds.put("Melissa Leo", "melissaleo");
	boxOfficeIds.put("Meryl Streep", "merylstreep");
	boxOfficeIds.put("Michael Douglas", "michaeldouglas");
	boxOfficeIds.put("Michelle Monaghan", "michellemonaghan");
	boxOfficeIds.put("Michelle Pfeiffer", "michellepfeiffer");
	boxOfficeIds.put("Michelle Williams", "michellewilliams");
	boxOfficeIds.put("Mike Leigh", "mikeleigh");
	boxOfficeIds.put("Mike Leigh - Another Year", "anotheryear");
	boxOfficeIds.put("Mila Kunis", "milakunis");
	boxOfficeIds.put("Miral", "miral");
	boxOfficeIds.put("Miranda Richardson", "mirandarichardson");
	boxOfficeIds.put("Monique", "monique");
	boxOfficeIds.put("Morgan Freeman", "morganfreeman");
	boxOfficeIds.put("Morning Glory", "morningglory");
	boxOfficeIds.put("Naomi Watts", "naomiwatts");
	boxOfficeIds.put("Natalie Portman", "natalieportman");
	boxOfficeIds.put("Nathalie Portman", "nathalieportman");
	boxOfficeIds.put("Neill Blomkamp", "neillblomkamp");
	boxOfficeIds.put("Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Nicole Kidman", "nicolekidman");
	boxOfficeIds.put("Nigel Cole - Made In Dagenham", "madeindagenham");
	boxOfficeIds.put("Nine", "nine");
	boxOfficeIds.put("Paul Giamatti - Barneys Version", "barneysversion");
	boxOfficeIds.put("Penelope Cruz", "penelopecruz");
	boxOfficeIds.put("Peter Jackson", "peterjackson");
	boxOfficeIds.put("Peter Weir", "peterweir");
	boxOfficeIds.put("Peter Weir - The Way Back", "wayback");
	boxOfficeIds.put("Precious", "preciouspush");
	boxOfficeIds.put("Quentin Tarantino", "quentintarantino");
	boxOfficeIds.put("Rabbit Hole", "rabbithole");
	boxOfficeIds.put("Rebecca Hall - The Town", "town10");
	boxOfficeIds.put("Rob Marshall", "robmarshall");
	boxOfficeIds.put("Robert Duvall - Get Low", "getlow");
	boxOfficeIds.put("Rosamund Pike", "rosamundpike");
	boxOfficeIds.put("Ruth Sheen - Another Year", "anotheryear");
	boxOfficeIds.put("Ryan Gosling - Blue Valentine", "bluevalentine");
	boxOfficeIds.put("Sally Hawkins", "sallyhawkins");
	boxOfficeIds.put("Sam Rockwell - Conviction", "conviction");
	boxOfficeIds.put("Samantha Morton", "samanthamorton");
	boxOfficeIds.put("Sandra Bullock", "sandrabullock");
	boxOfficeIds.put("Saoirse Ronan", "saoirseronan");
	boxOfficeIds.put("Sean Penn - Fair Game", "fairgame");
	boxOfficeIds.put("Secretariat", "secretariat");
	boxOfficeIds.put("Sherlock Holmes", "sherlockholmes");
	boxOfficeIds.put("Shutter Island", "shutterisland");
	boxOfficeIds.put("Sissy Spacek", "sissyspacek");
	boxOfficeIds.put("Sofia Coppola - Somewhere", "somewhere");
	boxOfficeIds.put("Somewhere", "somewhere");
	boxOfficeIds.put("Stanley Tucci", "stanleytucci");
	boxOfficeIds.put("Star Trek", "startrek");
	boxOfficeIds.put("Stephen Dorff - Somewhere", "somewhere");
	boxOfficeIds.put("Terrence Malick", "terrencemalick");
	boxOfficeIds.put("Terrence Malick - The Tree Of Life", "treeoflife");
	boxOfficeIds.put("The American", "american");
	boxOfficeIds.put("The Beaver", "beaver");
	boxOfficeIds.put("The Blind Side", "blindside");
	boxOfficeIds.put("The Conspirator", "conspirator");
	boxOfficeIds.put("The Debt", "debt");
	boxOfficeIds.put("The Ghost Writer", "ghostwriter");
	boxOfficeIds.put("The Hurt Locker", "hurtlocker");
	boxOfficeIds.put("The Lovely Bones", "lovelybones");
	boxOfficeIds.put("The Road", "road");
	boxOfficeIds.put("The Special Relationship", "specialrelationship");
	boxOfficeIds.put("The Town", "town10");
	boxOfficeIds.put("The Way Back", "wayback");
	boxOfficeIds.put("Up", "up");
	boxOfficeIds.put("Up In The Air", "upinair");
	boxOfficeIds.put("Vera Farmiga", "verafarmiga");
	boxOfficeIds.put("Vincent Cassel - Black Swan", "blackswan");
	boxOfficeIds.put("Wall Street: Money Never Sleeps",
		"wallstreet:moneyneversleeps");
	boxOfficeIds.put("Where The Wild Things Are", "wherewildthingsare");
	boxOfficeIds.put("Woody Harrelson", "woodyharrelson");
	boxOfficeIds.put("You Will Meet A Tall Dark Stranger",
		"youwillmeetatalldarkstranger");
	boxOfficeIds.put("A Dangerous Method", "adangerousmethod");
	boxOfficeIds.put("Aaron Schneider", "aaronschneider");
	boxOfficeIds.put("Albert Brooks - Drive", "drive");
	boxOfficeIds.put("Andrea Riseborough", "andreariseborough");
	boxOfficeIds.put("Andrew Garfield", "andrewgarfield");
	boxOfficeIds.put("Andrew Garfield - Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Andrew Garfield - The Social Network",
		"socialnetwork");
	boxOfficeIds.put("Anne Hathaway", "annehathaway");
	boxOfficeIds.put("Annette Bening", "annettebening");
	boxOfficeIds.put("Another Year", "anotheryear");
	boxOfficeIds.put("Armie Hammer - J Edgar", "jedgar");
	boxOfficeIds.put("Ben Affleck - The Town", "town10");
	boxOfficeIds.put("Ben Kingsley - Hugo Cabret", "hugocabret");
	boxOfficeIds.put("Elizabeth Olsen - Martha Marcy May Marelene",
		"marthamarcymaymarlene");
	boxOfficeIds.put("Elizabeth Olsen - Martha Marcy May Marelene",
		"marthamarcymaymarlene");
	boxOfficeIds.put("Bill Murray - Get Low", "getlow");
	boxOfficeIds.put("Biutiful", "biutiful");
	boxOfficeIds.put("Blue Valentine", "bluevalentine");
	boxOfficeIds.put("Bob Hoskins - Made in Dagenham", "madeindagenham");
	boxOfficeIds.put("John Hawkes - Martha Marcy May Marlene",
		"marthamarcymaymarlene");
	boxOfficeIds.put("Brad Pitt - The Tree of Life", "treeoflife");
	boxOfficeIds.put("Carey Mulligan", "careymulligan");
	boxOfficeIds.put("Carnage", "carnage");
	boxOfficeIds.put("Charlie Theron - Young Adult", "youngadult");
	boxOfficeIds.put("Charlize Theron - Young Adult", "youngadult");
	boxOfficeIds.put("Christoph Waltz - Carnage", "carnage");
	boxOfficeIds.put("Christopher Nolan - Inception", "inception");
	boxOfficeIds.put("Clint Eastwood - Hereafter", "hereafter");
	boxOfficeIds.put("Clint Eastwood - J Edgar", "jedgar");
	boxOfficeIds.put("Contagion", "contagion");
	boxOfficeIds.put("Corey Stoll - Midnight in Paris", "midnightinparis");
	boxOfficeIds.put("Danny Boyle - 127 Hours", "127hours");
	boxOfficeIds.put("David Cronenberg - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("David Fincher - The Girl With The Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("Debra Granik - Winters Bone", "wintersbone");
	boxOfficeIds.put("Derek Cianfrance", "derekcianfrance");
	boxOfficeIds.put("Diane Lane", "dianelane");
	boxOfficeIds.put("Doug Linman - Fair Game", "fairgame");
	boxOfficeIds.put("Drive", "drive");
	boxOfficeIds.put("Ed Harris - The Way Back", "wayback");
	boxOfficeIds.put("Edward Zwick - Love and Other Things",
		"loveandorthings");
	boxOfficeIds.put("Bennett Miller - Moneyball", "moneyball");
	boxOfficeIds.put("Evan Rachel Wood", "evanrachelwood");
	boxOfficeIds.put("Ezra Miller - We Need To Talk About Kevin",
		"weneedtotalkaboutkevin");
	boxOfficeIds.put("Fair Game", "fairgame");
	boxOfficeIds.put("Felicity Jones - Like Crazy", "likecrazy");
	boxOfficeIds.put("Garrett Hedlund - On The Road", "ontheroad");
	boxOfficeIds.put("George Clooney", "georgeclooney");
	boxOfficeIds.put("George Clooney - The Ides of March", "idesofmarch");
	boxOfficeIds.put("Get Low", "getlow");
	boxOfficeIds.put("Halle Berry - Frankie And Alice", "frankieandalice");
	boxOfficeIds.put("Harry Potter and The Deathly Hallows, Part II",
		"harrypotteranddeathlyhallows,partii");
	boxOfficeIds.put("Hereafter", "hereafter");
	boxOfficeIds.put("Hilary Swank", "hilaryswank");
	boxOfficeIds.put("How to Train Your Dragon", "howtotrainyourdragon");
	boxOfficeIds.put("J Edgar", "jedgar");
	boxOfficeIds.put("Jake Gyllenhaal", "jakegyllenhaal");
	boxOfficeIds.put("Jennifer Lawrence", "jenniferlawrence");
	boxOfficeIds.put("Jeremy Irvine - War Horse", "warhorse");
	boxOfficeIds.put("Jessica Chastain", "jessicachastain");
	boxOfficeIds.put("Jim Broadbent - Another Year", "anotheryear");
	boxOfficeIds.put("Jim Broadbent - The Iron Lady", "ironlady");
	boxOfficeIds.put("Jim Sturgess - The Way Back", "wayback");
	boxOfficeIds.put("Jodie Foster - Carnage", "carnage");
	boxOfficeIds.put("Joel &amp; Ethan Coen", "joel&amp;ethancoen");
	boxOfficeIds.put("John Cameron Mitchell", "johncameronmitchell");
	boxOfficeIds.put("Brad Pitt - Money Ball", "moneyball");
	boxOfficeIds.put("Joseph Gordon Levitt - 50/50", "50/50");
	boxOfficeIds.put("Josh Brolin - True Grit", "truegrit2010");
	boxOfficeIds.put("Jude Law - Contagion", "contagion");
	boxOfficeIds.put("Julia Roberts", "juliaroberts");
	boxOfficeIds.put("Julianne Moore", "juliannemoore");
	boxOfficeIds.put("Justin Timberlake - The Social Network",
		"socialnetwork");
	boxOfficeIds.put("Keira Knightley - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("Kirsten Dunst - Melancholia!", "melancholia!");
	boxOfficeIds.put("Lee Unkrich - Toy Story 3", "toystory3");
	boxOfficeIds.put("Leonardo DiCaprio - Inception", "inception");
	boxOfficeIds.put("Leonardo DiCaprio - J Edgar", "jedgar");
	boxOfficeIds.put("Lesley Manville", "lesleymanville");
	boxOfficeIds.put("Like Crazy", "likecrazy");
	boxOfficeIds.put("Lisa Cholodenko - The Kids Are All Right",
		"kidsareallright");
	boxOfficeIds.put("Love and Other Drugs", "loveandordrugs");
	boxOfficeIds.put("Made in Dagenham", "madeindagenham");
	boxOfficeIds.put("Mark Romanek - Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Mark Wahlberg - The Fighter", "fighter10");
	boxOfficeIds.put("Martha Marcy May Marlene", "marthamarcymaymarlene");
	boxOfficeIds.put("Martin Scorsese - Shutter Island", "shutterisland");
	boxOfficeIds.put("Matt Damon - Contagion", "contagion");
	boxOfficeIds.put("Matt Damon - Hereafter", "hereafter");
	boxOfficeIds.put("Matt Damon - True Grit", "truegrit2010");
	boxOfficeIds.put("Matt Damon - We Bought A Zoo", "weboughtazoo");
	boxOfficeIds.put("Mia Wasikowska", "miawasikowska");
	boxOfficeIds.put("Michael Douglas", "michaeldouglas");
	boxOfficeIds.put("Michael Fassbender - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("Michael Fassbender - Shame", "shame");
	boxOfficeIds.put("Michael Shannon - Take Shelter", "takeshelter");
	boxOfficeIds.put("Michelle Williams", "michellewilliams");
	boxOfficeIds.put("Michelle Yeoh - The Lady", "lady");
	boxOfficeIds.put("Mike Leigh - Another Year", "anotheryear");
	boxOfficeIds.put("Miral", "miral");
	boxOfficeIds.put("My Week With Marilyn", "myweekwithmarilyn");
	boxOfficeIds.put("Naomi Watts", "naomiwatts");
	boxOfficeIds.put("Natalie Portman", "natalieportman");
	boxOfficeIds.put("Never Let Me Go", "neverletmego");
	boxOfficeIds.put("Nicholas Winding Refn - Drive", "drive");
	boxOfficeIds.put("Nicolas Bro - War Horse", "warhorse");
	boxOfficeIds.put("Nicole Kidman", "nicolekidman");
	boxOfficeIds.put("Niels Arestrup - War Horse", "warhorse");
	boxOfficeIds.put("Nigel Cole - Made In Dagenham", "madeindagenham");
	boxOfficeIds.put("Octavia Spencer", "octaviaspencer");
	boxOfficeIds.put("Olivia Colman - Tyrannosaur", "tyrannosaur");
	boxOfficeIds.put("Patton Oswalt - Young Adult", "youngadult");
	boxOfficeIds.put("Paul Giamatti - Barneys Version", "barneysversion");
	boxOfficeIds.put("Peter Weir - The Way Back", "wayback");
	boxOfficeIds.put("Philip Seymour Hoffman - The Ides Of March",
		"idesofmarch");
	boxOfficeIds.put("Rabbit Hole", "rabbithole");
	boxOfficeIds.put("Robert Duvall - Get Low", "getlow");
	boxOfficeIds.put("Roman Polanski - Carnage", "carnage");
	boxOfficeIds.put("Rooney Mara - The Girl with the Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("Ryan Gosling - All Good Things", "allgoodthings");
	boxOfficeIds.put("Ryan Gosling - Blue Valentine", "bluevalentine");
	boxOfficeIds.put("Ryan Gosling - The Ides of March", "idesofmarch");
	boxOfficeIds.put("Sally Hawkins", "sallyhawkins");
	boxOfficeIds.put("Sam Rockwell - Conviction", "conviction");
	boxOfficeIds.put("Sandra Bullock", "sandrabullock");
	boxOfficeIds.put("Sean Penn - Fair Game", "fairgame");
	boxOfficeIds.put("Sean Penn - The Tree of Life", "treeoflife");
	boxOfficeIds.put("Secretariat", "secretariat");
	boxOfficeIds.put("Shame", "shame");
	boxOfficeIds.put("Shutter Island", "shutterisland");
	boxOfficeIds.put("Sofia Coppola - Somewhere", "somewhere");
	boxOfficeIds.put("Somewhere", "somewhere");
	boxOfficeIds.put(
		"Stephen Daldry - Extremely Loud and Incredibly Close",
		"extremelyloud");
	boxOfficeIds.put("Stephen Dorff - Somewhere", "somewhere");
	boxOfficeIds.put("Steve Mcqueen - Shame", "shame");
	boxOfficeIds.put("Steven Spielberg - War Horse", "warhorse");
	boxOfficeIds.put("Terrence Malick - The Tree Of Life", "treeoflife");
	boxOfficeIds.put("The American", "american");
	boxOfficeIds.put("The Girl with the Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("The Ides of March", "idesofmarch");
	boxOfficeIds.put("The Iron Lady", "ironlady");
	boxOfficeIds.put("The Town", "town10");
	boxOfficeIds.put("The Way Back", "wayback");
	boxOfficeIds.put("Tilda Swinton - We Need To Talk About Kevin",
		"weneedtotalkaboutkevin");
	boxOfficeIds.put("Tinker Tailor Soldier Spy", "tinkertailorsoldierspy");
	boxOfficeIds.put("Tom Hardy - Warrior", "warrior10");
	boxOfficeIds.put("Bennett Miller - Moneyball", "moneyball");
	boxOfficeIds.put("Vanessa Redgrave", "vanessaredgrave");
	boxOfficeIds.put("Viggo Mortensen - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("Vincent Cassel - Black Swan", "blackswan");
	boxOfficeIds.put("We Bought A Zoo", "weboughtazoo");
	boxOfficeIds.put("We Need to Talk About Kevin",
		"weneedtotalkaboutkevin");
	boxOfficeIds.put("Woody Harelson - Rampart", "rampart");
	boxOfficeIds.put("Young Adult", "youngadult");
	boxOfficeIds.put("Yun Jeong-Hie - Poetry", "poetry");
	boxOfficeIds.put("50/50", "50/50");
	boxOfficeIds.put("A Dangerous Method", "adangerousmethod");
	boxOfficeIds.put("james mcavoy - the disappearance eleanor rigby",
		"disappearanceeleanorrigby");
	boxOfficeIds.put("Brad Pitt", "bradpitt");
	boxOfficeIds.put("Carnage", "carnage");
	boxOfficeIds.put("Charlie Theron - Young Adult", "youngadult");
	boxOfficeIds.put("Charlize Theron - Young Adult", "youngadult");
	boxOfficeIds.put("Clint Eastwood - J Edgar", "jedgar");
	boxOfficeIds.put("Contagion", "contagion");
	boxOfficeIds.put("David Cronenberg - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("David Fincher - The Girl With The Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("Drive", "drive");
	boxOfficeIds.put("james mcavoy - the disappearance eleanor rigby",
		"disappearanceeleanorrigby");
	boxOfficeIds.put("Felicity Jones - Like Crazy", "likecrazy");
	boxOfficeIds.put("Gary Oldman", "garyoldman");
	boxOfficeIds.put("George Clooney - The Ides of March", "idesofmarch");
	boxOfficeIds.put("J Edgar", "jedgar");
	boxOfficeIds.put("Jean Dujardin", "jeandujardin");
	boxOfficeIds.put("Jeremy Irvine", "jeremyirvine");
	boxOfficeIds.put("Jeremy Irvine - War Horse", "warhorse");
	boxOfficeIds.put("Jodie Foster - Carnage", "carnage");
	boxOfficeIds.put("Joseph Gordon Levitt - 50/50", "50/50");
	boxOfficeIds.put("Keira Knightley - A Dangerous Method",
		"adangerousmethod");
	boxOfficeIds.put("Kirsten Dunst - Melancholia!", "melancholia!");
	boxOfficeIds.put("Leonardo Di Caprio", "leonardodicaprio");
	boxOfficeIds.put("Leonardo DiCaprio - J Edgar", "jedgar");
	boxOfficeIds.put("Martha Marcy May Marlene", "marthamarcymaymarlene");
	boxOfficeIds.put("Matt Damon - We Bought A Zoo", "weboughtazoo");
	boxOfficeIds.put("Michael Fassbender", "michaelfassbender");
	boxOfficeIds.put("Michael Fassbender - Shame", "shame");
	boxOfficeIds.put("Michael Shannon - Take Shelter", "takeshelter");
	boxOfficeIds.put("Michelle Yeoh - The Lady", "lady");
	boxOfficeIds.put("My Week With Marilyn", "myweekwithmarilyn");
	boxOfficeIds.put("Nicholas Winding Refn - Drive", "drive");
	boxOfficeIds.put("Olivia Colman - Tyrannosaur", "tyrannosaur");
	boxOfficeIds.put("Roman Polanski - Carnage", "carnage");
	boxOfficeIds.put("Rooney Mara - The Girl with the Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("Ryan Gosling", "ryangosling");
	boxOfficeIds.put("Ryan Gosling - The Ides of March", "idesofmarch");
	boxOfficeIds.put("Sean Penn", "seanpenn");
	boxOfficeIds.put("Sean Penn - The Tree of Life", "treeoflife");
	boxOfficeIds.put("Shame", "shame");
	boxOfficeIds.put(
		"Stephen Daldry - Extremely Loud and Incredibly Close",
		"extremelyloud");
	boxOfficeIds.put("Steve Mcqueen - Shame", "shame");
	boxOfficeIds.put("Steven Spielberg - War Horse", "warhorse");
	boxOfficeIds.put("The Girl with the Dragon Tattoo",
		"girlwiththedragontattoo");
	boxOfficeIds.put("The Ides of March", "idesofmarch");
	boxOfficeIds.put("The Iron Lady", "ironlady");
	boxOfficeIds.put("Tilda Swinton - We Need To Talk About Kevin",
		"weneedtotalkaboutkevin");
	boxOfficeIds.put("Tinker Tailor Soldier Spy", "tinkertailorsoldierspy");
	boxOfficeIds.put("Tom Hardy", "tomhardy");
	boxOfficeIds.put("Tom Hardy - Warrior", "warrior10");
	boxOfficeIds.put(
		"jessica chastain - the disappearance of eleanor rigby: them",
		"disappearanceofeleanorrigby:m");
	boxOfficeIds.put("We Bought A Zoo", "weboughtazoo");
	boxOfficeIds.put("We Need to Talk About Kevin",
		"weneedtotalkaboutkevin");
	boxOfficeIds.put("Woody Harelson - Rampart", "rampart");
	boxOfficeIds.put("Yun Jeong-Hie - Poetry", "poetry");
	boxOfficeIds
		.put("Brad Pitt - Killing Them Softly", "killingthemsoftly");
	boxOfficeIds.put("Chris ODowd - The Sapphires", "sapphires");
	boxOfficeIds.put("Elle Fanning - Ginger and Rosa", "gingerandrosa");
	boxOfficeIds.put("Ewan McGregor - The Impossible", "impossible");
	boxOfficeIds.put("Garrett Hedlund - On the Road", "ontheroad");
	boxOfficeIds
		.put("Michelle Williams - Take This Waltz", "takethiswaltz");
	boxOfficeIds.put("Tom Holland - The Impossible", "impossible");
	boxOfficeIds.put("Viola Davis - Wont Back Down", "wontbackdown");
	boxOfficeIds.put("Zoe Kazan - Ruby Sparks", "rubysparks");
	boxOfficeIds.put("a most violent year", "amostviolentyear");
	boxOfficeIds.put("adam sandler - men women and children",
		"menwomenandchildren");
	boxOfficeIds.put("al pacino - the humbling", "humbling");
	boxOfficeIds.put("albert brooks - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("alec baldwin - still alice", "stillalice");
	boxOfficeIds.put("alfred molina - love is strange", "loveisstrange");
	boxOfficeIds.put("amy adams - big eyes", "bigeyes");
	boxOfficeIds.put("analeigh tipton - two night stand", "twonightstand");
	boxOfficeIds.put("andy serkis - dawn of the planet of the apes",
		"dawnoftheapes");
	boxOfficeIds.put("angelina jolie - maleficent", "maleficient");
	boxOfficeIds.put("angelina jolie - unbroken", "unbroken");
	boxOfficeIds.put("anna kendrick - happy christmas", "happychristmas");
	boxOfficeIds.put("anna kendrick - into the woods", "intothewoods");
	boxOfficeIds.put("anne dorval - mommy", "mommy");
	boxOfficeIds.put("anne hathaway - interstellar", "interstellar");
	boxOfficeIds.put("annette bening - the search", "search");
	boxOfficeIds.put("audrey tautou - mood indigo", "moodindigo");
	boxOfficeIds.put("ava duvernay - selma", "selma");
	boxOfficeIds.put("begin again", "beginagain");
	boxOfficeIds.put("ben affleck - gone girl", "gonegirl");
	boxOfficeIds.put("berenice bejo - the search", "search");
	boxOfficeIds.put("big eyes", "bigeyes");
	boxOfficeIds.put("bill hader - the skeleton twins", "skeletontwins");
	boxOfficeIds.put("bill murray - st vincent", "stvincent");
	boxOfficeIds.put("brad pitt - fury", "fury");
	boxOfficeIds.put("brendan gleeson - calvary", "calvary");
	boxOfficeIds.put("calvary", "calvary");
	boxOfficeIds.put("carey mulligan - suffragette", "suffragette");
	boxOfficeIds.put("carmen ejogo - selma", "selma");
	boxOfficeIds.put("carrie coon - gone girl", "gonegirl");
	boxOfficeIds.put("casey affleck - interstellar", "interstellar");
	boxOfficeIds.put("chadwick boseman - get on up", "getonup");
	boxOfficeIds.put("channing tatum - foxcatcher", "foxcatcher");
	boxOfficeIds.put("charlotte gainsbourg - nymphomaniac vol 2",
		"nymphomaniacvol2");
	boxOfficeIds.put("chris rock - top five", "topfive");
	boxOfficeIds.put("christian bale - exodus: gods and kings",
		"exodus:godsandkings");
	boxOfficeIds.put("christoph waltz - big eyes", "bigeyes");
	boxOfficeIds.put("christopher nolan - interstellar", "interstellar");
	boxOfficeIds.put("christopher plummer - elsa and fred", "elsaandfred");
	boxOfficeIds.put("christopher walken - jersey boys", "jerseyboys");
	boxOfficeIds.put("christopher waltz - big eyes", "bigeyes");
	boxOfficeIds.put("clint eastwood - american sniper", "americansniper");
	boxOfficeIds.put("colin firth - magic in the moonlight",
		"magicinthemoonlight");
	boxOfficeIds.put("dakota fanning - night moves", "nightmoves");
	boxOfficeIds.put("dame maggie smith - my old lady", "myoldlady");
	boxOfficeIds.put("damien chazelle - whiplash", "whiplash");
	boxOfficeIds.put("david ayer - fury", "fury");
	boxOfficeIds.put("david cronenburg - maps to the stars",
		"mapstothestars");
	boxOfficeIds.put("david fincher - gone girl", "gonegirl");
	boxOfficeIds.put("david oyelowo - selma", "selma");
	boxOfficeIds.put("domhall gleeson - unbroken", "unbroken");
	boxOfficeIds.put("domhnall gleeson - unbroken", "unbroken");
	boxOfficeIds.put("dorothy atkinson - mr turner", "mrturner");
	boxOfficeIds.put("elisabeth moss - the one i love", "oneilove");
	boxOfficeIds.put("ellar coltrane - boyhood", "boyhood");
	boxOfficeIds.put("emily blunt - into the woods", "intothewoods");
	boxOfficeIds.put("emma stone - magic in the moonlight",
		"magicinthemoonlight");
	boxOfficeIds.put("emma watson - noah", "noah");
	boxOfficeIds.put("f murray abraham - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("foxcatcher", "foxcatcher");
	boxOfficeIds.put("fury", "fury");
	boxOfficeIds.put("gael garcia bernal - rosewater", "rosewater");
	boxOfficeIds.put("gary oldman - child 44", "child44");
	boxOfficeIds.put("get on up", "getonup");
	boxOfficeIds.put("gone girl", "gonegirl");
	boxOfficeIds.put("grace of monaco", "graceofmonaco");
	boxOfficeIds.put("greta gerwig - the humbling", "humbling");
	boxOfficeIds.put("gugu mbatha-raw - belle", "belle");
	boxOfficeIds.put("gugu mbatha-raw - beyond the lights",
		"beyondthelights");
	boxOfficeIds.put("helen mirren - the hundred foot journey",
		"hundredfootjourney");
	boxOfficeIds.put("helena bonham carter - suffragette", "suffragette");
	boxOfficeIds.put("hilary swank - the homesman", "homesman");
	boxOfficeIds.put("hilary swank - youre not you", "yourenotyou");
	boxOfficeIds.put("horrible bosses 2", "horriblebosses2");
	boxOfficeIds.put("inherent vice", "inherentvice");
	boxOfficeIds.put("interstellar", "interstellar");
	boxOfficeIds.put("into the woods", "intothewoods");
	boxOfficeIds.put("jack oconnell - unbroken", "unbroken");
	boxOfficeIds.put("jake gyllenhaal - nightcrawler", "nightcrawlers");
	boxOfficeIds.put("james corden - into the woods", "intothewoods");
	boxOfficeIds.put("james gandolfini - the drop", "drop");
	boxOfficeIds.put("james marsh - theory of everything",
		"oryofeverything");
	boxOfficeIds.put("james mcavoy - filth", "filth");
	boxOfficeIds.put(
		"jessica chastain - the disappearance of eleanor rigby: them",
		"disappearanceofeleanorrigby:m");
	boxOfficeIds.put("jamie foxx - annie", "annie");
	boxOfficeIds
		.put("jc chandor - a most violent year", "amostviolentyear");
	boxOfficeIds.put("jean-marc vallee - wild", "wild2014");
	boxOfficeIds.put("jennifer aniston - cake", "cake");
	boxOfficeIds.put(
		"jennifer lawrence - the hunger games: mockingjay part 1",
		"hungergames:mockingjaypart1");
	boxOfficeIds.put("jenny slate - obvious child", "obviouschild");
	boxOfficeIds.put("jeremy renner - kill the messenger",
		"killthemessenger");
	boxOfficeIds.put("jessica chastain - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("jessica chastain - interstellar", "interstellar");
	boxOfficeIds.put("Benedict Cumberbatch - Tinker Taylor Soldier Spy",
		"tinkertailorsoldierspy");
	boxOfficeIds.put("jessica lange - the gambler", "gambler");
	boxOfficeIds.put("joaquin phoenix - inherent vice", "inherentvice");
	boxOfficeIds.put("joaquin phoenix - the immigrant", "immigrant");
	boxOfficeIds.put("john cusack - maps to the stars", "mapstothestars");
	boxOfficeIds.put("john goodman - the gambler", "gambler");
	boxOfficeIds.put("john hawkes - low down", "lowdown");
	boxOfficeIds.put("john lithgow - love is strange", "loveisstrange");
	boxOfficeIds.put("johnny depp - into the woods", "intothewoods");
	boxOfficeIds.put("jon stewart - rosewater", "rosewater");
	boxOfficeIds.put("josh brolin - inherent vice", "inherentvice");
	boxOfficeIds
		.put("julianne moore - maps to the stars", "mapstothestars");
	boxOfficeIds.put("juliette binoche - clouds of sils maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("katherine waterson - inherent vice", "inherentvice");
	boxOfficeIds.put("katherine waterston - inherent vice", "inherentvice");
	boxOfficeIds.put("keira knightley - begin again", "beginagain");
	boxOfficeIds.put("kill the messenger", "killthemessenger");
	boxOfficeIds.put("kirsten dunst - the two faces of january",
		"twofacesofjanuary");
	boxOfficeIds.put("kristen stewart - camp x-ray", "campx-ray");
	boxOfficeIds.put("kristen stewart - clouds of sils maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("kristen stewart - still alice", "stillalice");
	boxOfficeIds.put("kristen wiig - the skeleton twins", "skeletontwins");
	boxOfficeIds.put("kristin scott thomas - suite francaise",
		"suitefrancaise");
	boxOfficeIds.put("life itself", "lifeitself");
	boxOfficeIds.put("logan lerman - fury", "fury");
	boxOfficeIds.put("love is strange", "loveisstrange");
	boxOfficeIds.put("maggie gyllenhaal - frank", "frank");
	boxOfficeIds.put("maggie smith - my old lady", "myoldlady");
	boxOfficeIds.put("magic in the moonlight", "magicinthemoonlight");
	boxOfficeIds.put("maps to the stars", "mapstothestars");
	boxOfficeIds.put("margot robbie - suite francaise", "suitefrancaise");
	boxOfficeIds.put("marion cotillard - the immigrant", "immigrant");
	boxOfficeIds.put("marisa tomei - love is strange", "loveisstrange");
	boxOfficeIds.put("mark ruffalo - begin again", "beginagain");
	boxOfficeIds.put("mark wahlberg - the gambler", "gambler");
	boxOfficeIds.put("mary elizabeth winstead - kill the messenger",
		"killthemessenger");
	boxOfficeIds.put("matthew mcconaughey - interstellar", "interstellar");
	boxOfficeIds.put("maya rudolph - inherent vice", "inherentvice");
	boxOfficeIds.put("men, women and children", "men,womenandchildren");
	boxOfficeIds.put("mia wasikowska - tracks", "tracks");
	boxOfficeIds.put("michael caine - interstellar", "interstellar");
	boxOfficeIds.put("michael fassbender - frank", "frank");
	boxOfficeIds.put("michelle monaghan - fort bliss", "fortbliss");
	boxOfficeIds.put("michelle williams - suite francaise",
		"suitefrancaise");
	boxOfficeIds.put("mike leigh - mr turner", "mrturner");
	boxOfficeIds.put("miles teller - whiplash", "whiplash");
	boxOfficeIds.put("miranda otto - the homesman", "homesman");
	boxOfficeIds.put("miyavi - unbroken", "unbroken");
	boxOfficeIds.put("mr turner", "mrturner");
	boxOfficeIds.put("naomi watts - birdman", "birdman");
	boxOfficeIds.put("naomi watts - st vincent", "stvincent");
	boxOfficeIds.put("neil patrick harris - gone girl", "gonegirl");
	boxOfficeIds.put("nicole kidman - before i go to sleep",
		"beforeigotosleep");
	boxOfficeIds.put("night at the museum: secret of the tomb",
		"nightatmuseum:secretoftomb");
	boxOfficeIds.put("nightcrawler", "nightcrawlers");
	boxOfficeIds.put("octavia spencer - get on up", "getonup");
	boxOfficeIds.put("oprah winfrey - selma", "selma");
	boxOfficeIds.put("oscar isaac - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("owen wilson - inherent vice", "inherentvice");
	boxOfficeIds
		.put("paul thomas anderson - inherent vice", "inherentvice");
	boxOfficeIds.put("pawn sacrifice", "pawnsacrifice");
	boxOfficeIds.put("penguins of madagascar", "penguinsofmadagascar");
	boxOfficeIds.put("philip seymour hoffman - a most wanted man",
		"amostwantedman");
	boxOfficeIds.put("pride", "pride");
	boxOfficeIds.put("quvenzhane wallis - annie", "annie");
	boxOfficeIds
		.put("rachel mcadams - a most wanted man", "amostwantedman");
	boxOfficeIds.put("ralph fiennes - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("reese witherspoon - inherent vice", "inherentvice");
	boxOfficeIds.put("reese witherspoon - the good lie", "goodlie");
	boxOfficeIds.put("rene russo - nightcrawler", "nightcrawlers");
	boxOfficeIds.put("ridley scott - exodus: gods and kings",
		"exodus:godsandkings");
	boxOfficeIds.put("rob marshall - into the woods", "intothewoods");
	boxOfficeIds.put("robin wright - the congress", "congress");
	boxOfficeIds.put("rosewater", "rosewater");
	boxOfficeIds.put("scarlett johansson - under the skin", "undertheskin");
	boxOfficeIds.put("shailene woodley - the fault in our stars",
		"thefaultinourstars");
	boxOfficeIds.put("shailene woodley - white bird in a blizzard",
		"whitebirdinablizzard");
	boxOfficeIds.put("shirley maclaine - elsa and fred", "elsaandfred");
	boxOfficeIds.put("shohreh aghdashloo - rosewater", "rosewater");
	boxOfficeIds.put("sienna miller - american sniper", "americansniper");
	boxOfficeIds.put("sigourney weaver - exodus", "exodus");
	boxOfficeIds.put("st vincent", "stvincent");
	boxOfficeIds.put("stellan skarsgard - nymphomaniac vol 2",
		"nymphomaniacvol2");
	boxOfficeIds.put("suffragette", "suffragette");
	boxOfficeIds.put("suite francaise", "suitefrancaise");
	boxOfficeIds.put("the cobbler", "cobbler");
	boxOfficeIds.put("the disappearance of eleanor rigby",
		"disappearanceofeleanorrigby");
	boxOfficeIds.put("the drop", "drop");
	boxOfficeIds.put("the fault in our stars", "thefaultinourstars");
	boxOfficeIds.put("the homeman", "homeman");
	boxOfficeIds.put("the hunger games: mockingjay - part 1",
		"hungergames:mockingjaypart1");
	boxOfficeIds.put("the judge", "judge");
	boxOfficeIds.put("the search", "search");
	boxOfficeIds.put("the two faces of journey", "twofacesofjourney");
	boxOfficeIds.put("tilda swinton - only lovers left alive",
		"onlyloversleftalive");
	boxOfficeIds.put("tilda swinton - snowpiercer", "snowpiercer");
	boxOfficeIds.put("tim burton - big eyes", "bigeyes");
	boxOfficeIds.put("tim roth - selma", "selma");
	boxOfficeIds.put("timothy spall - mr turner", "mrturner");
	boxOfficeIds.put("tom hardy - locke", "locke");
	boxOfficeIds.put("tom hardy - the drop", "drop");
	boxOfficeIds.put("tom wilkinson - selma", "selma");
	boxOfficeIds.put("tommy lee jones - the homesman", "homesman");
	boxOfficeIds.put("tony revoloti - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("trash", "trash");
	boxOfficeIds.put("true story", "truestory");
	boxOfficeIds.put("tyler perry - gone girl", "gonegirl");
	boxOfficeIds.put("unbroken", "unbroken");
	boxOfficeIds.put("vanessa redgrave - foxcatcher", "foxcatcher");
	boxOfficeIds.put("vera farmiga - the judge", "judge");
	boxOfficeIds.put("viggo mortensen - the two faces of january",
		"twofacesofjanuary");
	boxOfficeIds.put("viola davis - get on up", "getonup");
	boxOfficeIds.put("wash westmoreland and richard glatzer - still alice",
		"stillalice");
	boxOfficeIds.put("while were young", "whilewereyoung");
	boxOfficeIds.put("wild", "wild2014");
	boxOfficeIds.put("a most violent year", "amostviolentyear");
	boxOfficeIds.put("adam sandler - men women and children",
		"menwomenandchildren");
	boxOfficeIds.put("al pacino - the humbling", "humbling");
	boxOfficeIds.put("albert brooks - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("alec baldwin - still alice", "stillalice");
	boxOfficeIds.put("alfred molina - love is strange", "loveisstrange");
	boxOfficeIds.put("amy adams - big eyes", "bigeyes");
	boxOfficeIds.put("analeigh tipton - two night stand", "twonightstand");
	boxOfficeIds.put("andy serkis - dawn of the planet of the apes",
		"dawnoftheapes");
	boxOfficeIds.put("angelina jolie - maleficent", "maleficient");
	boxOfficeIds.put("angelina jolie - unbroken", "unbroken");
	boxOfficeIds.put("anna kendrick - happy christmas", "happychristmas");
	boxOfficeIds.put("anna kendrick - into the woods", "intothewoods");
	boxOfficeIds.put("anne dorval - mommy", "mommy");
	boxOfficeIds.put("anne hathaway - interstellar", "interstellar");
	boxOfficeIds.put("annette bening - the search", "search");
	boxOfficeIds.put("audrey tautou - mood indigo", "moodindigo");
	boxOfficeIds.put("ava duvernay - selma", "selma");
	boxOfficeIds.put("begin again", "beginagain");
	boxOfficeIds.put("ben affleck - gone girl", "gonegirl");
	boxOfficeIds.put("berenice bejo - the search", "search");
	boxOfficeIds.put("big eyes", "bigeyes");
	boxOfficeIds.put("bill hader - the skeleton twins", "skeletontwins");
	boxOfficeIds.put("bill murray - st vincent", "stvincent");
	boxOfficeIds.put("brad pitt - fury", "fury");
	boxOfficeIds.put("brendan gleeson - calvary", "calvary");
	boxOfficeIds.put("calvary", "calvary");
	boxOfficeIds.put("carey mulligan - suffragette", "suffragette");
	boxOfficeIds.put("carmen ejogo - selma", "selma");
	boxOfficeIds.put("carrie coon - gone girl", "gonegirl");
	boxOfficeIds.put("casey affleck - interstellar", "interstellar");
	boxOfficeIds.put("chadwick boseman - get on up", "getonup");
	boxOfficeIds.put("channing tatum - foxcatcher", "foxcatcher");
	boxOfficeIds.put("charlotte gainsbourg - nymphomaniac vol 2",
		"nymphomaniacvol2");
	boxOfficeIds.put("chris rock - top five", "topfive");
	boxOfficeIds.put("christian bale - exodus: gods and kings",
		"exodus:godsandkings");
	boxOfficeIds.put("christoph waltz - big eyes", "bigeyes");
	boxOfficeIds.put("christopher nolan - interstellar", "interstellar");
	boxOfficeIds.put("christopher plummer - elsa and fred", "elsaandfred");
	boxOfficeIds.put("christopher walken - jersey boys", "jerseyboys");
	boxOfficeIds.put("christopher waltz - big eyes", "bigeyes");
	boxOfficeIds.put("clint eastwood - american sniper", "americansniper");
	boxOfficeIds.put("colin firth - magic in the moonlight",
		"magicinthemoonlight");
	boxOfficeIds.put("dakota fanning - night moves", "nightmoves");
	boxOfficeIds.put("dame maggie smith - my old lady", "myoldlady");
	boxOfficeIds.put("damien chazelle - whiplash", "whiplash");
	boxOfficeIds.put("david ayer - fury", "fury");
	boxOfficeIds.put("david cronenburg - maps to the stars",
		"mapstothestars");
	boxOfficeIds.put("david fincher - gone girl", "gonegirl");
	boxOfficeIds.put("david oyelowo - selma", "selma");
	boxOfficeIds.put("domhnall gleeson - unbroken", "unbroken");
	boxOfficeIds.put("dorothy atkinson - mr turner", "mrturner");
	boxOfficeIds.put("elisabeth moss - the one i love", "oneilove");
	boxOfficeIds.put("ellar coltrane - boyhood", "boyhood");
	boxOfficeIds.put("emily blunt - into the woods", "intothewoods");
	boxOfficeIds.put("emma stone - magic in the moonlight",
		"magicinthemoonlight");
	boxOfficeIds.put("emma watson - noah", "noah");
	boxOfficeIds.put("f murray abraham - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("foxcatcher", "foxcatcher");
	boxOfficeIds.put("fury", "fury");
	boxOfficeIds.put("gael garcia bernal - rosewater", "rosewater");
	boxOfficeIds.put("gary oldman - child 44", "child44");
	boxOfficeIds.put("get on up", "getonup");
	boxOfficeIds.put("gone girl", "gonegirl");
	boxOfficeIds.put("grace of monaco", "graceofmonaco");
	boxOfficeIds.put("greta gerwig - the humbling", "humbling");
	boxOfficeIds.put("gugu mbatha-raw - belle", "belle");
	boxOfficeIds.put("gugu mbatha-raw - beyond the lights",
		"beyondthelights");
	boxOfficeIds.put("helen mirren - the hundred foot journey",
		"hundredfootjourney");
	boxOfficeIds.put("helena bonham carter - suffragette", "suffragette");
	boxOfficeIds.put("hilary swank - the homesman", "homesman");
	boxOfficeIds.put("hilary swank - youre not you", "yourenotyou");
	boxOfficeIds.put("horrible bosses 2", "horriblebosses2");
	boxOfficeIds.put("inherent vice", "inherentvice");
	boxOfficeIds.put("interstellar", "interstellar");
	boxOfficeIds.put("into the woods", "intothewoods");
	boxOfficeIds.put("jack oconnell - unbroken", "unbroken");
	boxOfficeIds.put("jake gyllenhaal - nightcrawler", "nightcrawlers");
	boxOfficeIds.put("james corden - into the woods", "intothewoods");
	boxOfficeIds.put("james gandolfini - the drop", "drop");
	boxOfficeIds.put("james marsh - theory of everything",
		"oryofeverything");
	boxOfficeIds.put("james mcavoy - filth", "filth");
	boxOfficeIds.put("Tomas Alfredson - Tinker Tailor Soldier Spy",
		"tinkertailorsoldierspy");
	boxOfficeIds.put("jamie foxx - annie", "annie");
	boxOfficeIds
		.put("jc chandor - a most violent year", "amostviolentyear");
	boxOfficeIds.put("jean-marc vallee - wild", "wild2014");
	boxOfficeIds.put("jennifer aniston - cake", "cake");
	boxOfficeIds.put(
		"jennifer lawrence - the hunger games: mockingjay part 1",
		"hungergames:mockingjaypart1");
	boxOfficeIds.put("jenny slate - obvious child", "obviouschild");
	boxOfficeIds.put("jeremy renner - kill the messenger",
		"killthemessenger");
	boxOfficeIds.put("jessica chastain - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("jessica chastain - interstellar", "interstellar");
	boxOfficeIds.put("Tomas Alfredson - Tinker Tailor Soldier Spy",
		"tinkertailorsoldierspy");
	boxOfficeIds.put("jessica lange - the gambler", "gambler");
	boxOfficeIds.put("joaquin phoenix - inherent vice", "inherentvice");
	boxOfficeIds.put("joaquin phoenix - the immigrant", "immigrant");
	boxOfficeIds.put("john cusack - maps to the stars", "mapstothestars");
	boxOfficeIds.put("john goodman - the gambler", "gambler");
	boxOfficeIds.put("john hawkes - low down", "lowdown");
	boxOfficeIds.put("john lithgow - love is strange", "loveisstrange");
	boxOfficeIds.put("johnny depp - into the woods", "intothewoods");
	boxOfficeIds.put("jon stewart - rosewater", "rosewater");
	boxOfficeIds.put("josh brolin - inherent vice", "inherentvice");
	boxOfficeIds
		.put("julianne moore - maps to the stars", "mapstothestars");
	boxOfficeIds.put("juliette binoche - clouds of sils maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("katherine waterston - inherent vice", "inherentvice");
	boxOfficeIds.put("keira knightley - begin again", "beginagain");
	boxOfficeIds.put("kill the messenger", "killthemessenger");
	boxOfficeIds.put("kirsten dunst - the two faces of january",
		"twofacesofjanuary");
	boxOfficeIds.put("kristen stewart - camp x-ray", "campx-ray");
	boxOfficeIds.put("kristen stewart - clouds of sils maria",
		"cloudsofsilsmaria");
	boxOfficeIds.put("kristen stewart - still alice", "stillalice");
	boxOfficeIds.put("kristen wiig - the skeleton twins", "skeletontwins");
	boxOfficeIds.put("kristin scott thomas - suite francaise",
		"suitefrancaise");
	boxOfficeIds.put("life itself", "lifeitself");
	boxOfficeIds.put("logan lerman - fury", "fury");
	boxOfficeIds.put("love is strange", "loveisstrange");
	boxOfficeIds.put("maggie gyllenhaal - frank", "frank");
	boxOfficeIds.put("maggie smith - my old lady", "myoldlady");
	boxOfficeIds.put("magic in the moonlight", "magicinthemoonlight");
	boxOfficeIds.put("maps to the stars", "mapstothestars");
	boxOfficeIds.put("margot robbie - suite francaise", "suitefrancaise");
	boxOfficeIds.put("marion cotillard - the immigrant", "immigrant");
	boxOfficeIds.put("marisa tomei - love is strange", "loveisstrange");
	boxOfficeIds.put("mark ruffalo - begin again", "beginagain");
	boxOfficeIds.put("mark wahlberg - the gambler", "gambler");
	boxOfficeIds.put("mary elizabeth winstead - kill the messenger",
		"killthemessenger");
	boxOfficeIds.put("matthew mcconaughey - interstellar", "interstellar");
	boxOfficeIds.put("maya rudolph - inherent vice", "inherentvice");
	boxOfficeIds.put("men, women and children", "men,womenandchildren");
	boxOfficeIds.put("mia wasikowska - tracks", "tracks");
	boxOfficeIds.put("michael caine - interstellar", "interstellar");
	boxOfficeIds.put("michael fassbender - frank", "frank");
	boxOfficeIds.put("michelle monaghan - fort bliss", "fortbliss");
	boxOfficeIds.put("michelle williams - suite francaise",
		"suitefrancaise");
	boxOfficeIds.put("mike leigh - mr turner", "mrturner");
	boxOfficeIds.put("miles teller - whiplash", "whiplash");
	boxOfficeIds.put("miranda otto - the homesman", "homesman");
	boxOfficeIds.put("miyavi - unbroken", "unbroken");
	boxOfficeIds.put("mr turner", "mrturner");
	boxOfficeIds.put("naomi watts - birdman", "birdman");
	boxOfficeIds.put("naomi watts - st vincent", "stvincent");
	boxOfficeIds.put("neil patrick harris - gone girl", "gonegirl");
	boxOfficeIds.put("nicole kidman - before i go to sleep",
		"beforeigotosleep");
	boxOfficeIds.put("night at the museum: secret of the tomb",
		"nightatmuseum:secretoftomb");
	boxOfficeIds.put("nightcrawler", "nightcrawlers");
	boxOfficeIds.put("octavia spencer - get on up", "getonup");
	boxOfficeIds.put("oprah winfrey - selma", "selma");
	boxOfficeIds.put("oscar isaac - a most violent year",
		"amostviolentyear");
	boxOfficeIds.put("owen wilson - inherent vice", "inherentvice");
	boxOfficeIds
		.put("paul thomas anderson - inherent vice", "inherentvice");
	boxOfficeIds.put("pawn sacrifice", "pawnsacrifice");
	boxOfficeIds.put("penguins of madagascar", "penguinsofmadagascar");
	boxOfficeIds.put("philip seymour hoffman - a most wanted man",
		"amostwantedman");
	boxOfficeIds.put("pride", "pride");
	boxOfficeIds.put("quvenzhane wallis - annie", "annie");
	boxOfficeIds
		.put("rachel mcadams - a most wanted man", "amostwantedman");
	boxOfficeIds.put("ralph fiennes - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("reese witherspoon - inherent vice", "inherentvice");
	boxOfficeIds.put("reese witherspoon - the good lie", "goodlie");
	boxOfficeIds.put("rene russo - nightcrawler", "nightcrawlers");
	boxOfficeIds.put("ridley scott - exodus: gods and kings",
		"exodus:godsandkings");
	boxOfficeIds.put("rob marshall - into the woods", "intothewoods");
	boxOfficeIds.put("robin wright - the congress", "congress");
	boxOfficeIds.put("rosewater", "rosewater");
	boxOfficeIds.put("scarlett johansson - under the skin", "undertheskin");
	boxOfficeIds.put("shailene woodley - the fault in our stars",
		"thefaultinourstars");
	boxOfficeIds.put("shailene woodley - white bird in a blizzard",
		"whitebirdinablizzard");
	boxOfficeIds.put("shirley maclaine - elsa and fred", "elsaandfred");
	boxOfficeIds.put("shohreh aghdashloo - rosewater", "rosewater");
	boxOfficeIds.put("sienna miller - american sniper", "americansniper");
	boxOfficeIds.put("sigourney weaver - exodus", "exodus");
	boxOfficeIds.put("st vincent", "stvincent");
	boxOfficeIds.put("stellan skarsgard - nymphomaniac vol 2",
		"nymphomaniacvol2");
	boxOfficeIds.put("suffragette", "suffragette");
	boxOfficeIds.put("suite francaise", "suitefrancaise");
	boxOfficeIds.put("the cobbler", "cobbler");
	boxOfficeIds.put("the disappearance of eleanor rigby",
		"disappearanceofeleanorrigby");
	boxOfficeIds.put("the drop", "drop");
	boxOfficeIds.put("the fault in our stars", "thefaultinourstars");
	boxOfficeIds.put("the homeman", "homeman");
	boxOfficeIds.put("the hunger games: mockingjay - part 1",
		"hungergames:mockingjaypart1");
	boxOfficeIds.put("the judge", "judge");
	boxOfficeIds.put("the search", "search");
	boxOfficeIds.put("the two faces of journey", "twofacesofjourney");
	boxOfficeIds.put("tilda swinton - only lovers left alive",
		"onlyloversleftalive");
	boxOfficeIds.put("tilda swinton - snowpiercer", "snowpiercer");
	boxOfficeIds.put("tim burton - big eyes", "bigeyes");
	boxOfficeIds.put("tim roth - selma", "selma");
	boxOfficeIds.put("timothy spall - mr turner", "mrturner");
	boxOfficeIds.put("tom hardy - locke", "locke");
	boxOfficeIds.put("tom hardy - the drop", "drop");
	boxOfficeIds.put("tom wilkinson - selma", "selma");
	boxOfficeIds.put("tommy lee jones - the homesman", "homesman");
	boxOfficeIds.put("tony revoloti - the grand budapest hotel",
		"grandbudapesthotel");
	boxOfficeIds.put("trash", "trash");
	boxOfficeIds.put("true story", "truestory");
	boxOfficeIds.put("tyler perry - gone girl", "gonegirl");
	boxOfficeIds.put("unbroken", "unbroken");
	boxOfficeIds.put("vanessa redgrave - foxcatcher", "foxcatcher");
	boxOfficeIds.put("vera farmiga - the judge", "judge");
	boxOfficeIds.put("viggo mortensen - the two faces of january",
		"twofacesofjanuary");
	boxOfficeIds.put("viola davis - get on up", "getonup");
	boxOfficeIds.put("wash westmoreland and richard glatzer - still alice",
		"stillalice");
	boxOfficeIds.put("while were young", "whilewereyoung");
	boxOfficeIds.put("wild", "wild2014");

	won = new HashMap<String, Integer>();
	won.put("artist", 1);
	won.put("descendents", 1);
	won.put("help2011", 1);
	won.put("hugocabret", 1);
	won.put("warhorse", 0);
	won.put("extremelyloud", 0);
	won.put("treeoflife", 0);
	won.put("midnightinparis", 1);
	won.put("moneyball", 0);
	won.put("tinkertailorsoldierspy", 0);
	won.put("abetterlife", 0);
	won.put("wiigapatow", 0);
	won.put("albertnobbs", 0);
	won.put("lincoln", 1);
	won.put("themaster", 0);
	won.put("djangounchained", 1);
	won.put("silverliningsplaybook", 1);
	won.put("argo", 1);
	won.put("lifeofpi", 1);
	won.put("amour", 1);
	won.put("beastsofthesouthernwild", 0);
	won.put("lesmiserables2012", 1);
	won.put("flight", 0);
	won.put("binladen", 1);
	won.put("impossible", 0);
	won.put("hitchcock", 0);
	won.put("twelveyearsaslave", 1);
	won.put("gravity", 1);
	won.put("davido2013", 0);
	won.put("avengers11", 0);
	won.put("wolfofwallstreet", 0);
	won.put("captainphillips", 0);
	won.put("nebraska", 0);
	won.put("her2013", 1);
	won.put("philomena", 0);
	won.put("sixsessions", 0);
	won.put("bluejasmine", 1);
	won.put("dallasbuyersclub", 1);
	won.put("bestexoticmarigoldhotel", 0);
	won.put("brave", 0);
	won.put("augustosagecounty", 0);
	won.put("birdman", 1);
	won.put("boyhood", 1);
	won.put("imitationgame", 1);
	won.put("grandbudapesthotel", 1);
	won.put("americansniper", 1);
	won.put("whiplash", 1);
	won.put("theoryofeverything", 1);
	won.put("selma", 0);
	won.put("foxcatcher", 0);
	won.put("stillalice", 1);
	won.put("gonegirl", 0);
	won.put("wild2014", 0);
	won.put("twodaysonenight", 0);
	won.put("judge", 0);
	won.put("troubewiththecurve", 0);
	won.put("cloudatlas", 0);
	won.put("darkknightrises", 0);
	won.put("insidellewyndavis", 0);
	won.put("greatgatsby", 1);
	won.put("moonrisekingdom", 0);
	won.put("bond23", 1);
	won.put("hobbit", 0);
	won.put("annakarenina2012", 1);
	won.put("hydeparkonhudson", 0);
	won.put("killingthemsoftly", 0);
	won.put("placebeyondthepines", 0);
	won.put("darkknight", 1);
	won.put("promisedland", 0);
	won.put("rustandbone", 0);
	won.put("teleportationaccident", 0);
	won.put("sapphires", 0);
	won.put("trumbo", 0);
	won.put("suffragette", 0);
	won.put("spotlight", 0);
	won.put("youth", 0);
	won.put("bridgeofspies", 0);
	won.put("blackmass", 0);
	won.put("brooklyn", 0);
	won.put("nedbeauman", 0);
	won.put("placebeyondpines", 0);
	won.put("troublewithcurve", 0);
	won.put("killingmsoftly", 0);
	won.put("benhzeitlin", 0);
	won.put("arbitrage", 0);
	won.put("hopesprings", 0);
	won.put("endofwatch", 0);
	won.put("reseraquin", 0);
	won.put("wontbackdown", 0);
	won.put("smashed", 0);
	won.put("quartet", 0);
	won.put("francesha", 0);
	won.put("savingmrbanks", 0);
	won.put("mandela:longwalktofreedom", 0);
	won.put("blueiswarmestcolour", 0);
	won.put("butler", 0);
	won.put("allislost", 0);
	won.put("laborday", 0);
	won.put("monumentsmen", 0);
	won.put("beforemidnight", 0);
	won.put("fruitvalestation", 0);
	won.put("graceofmonaco", 0);
	won.put("mud", 0);
	won.put("rush", 0);
	won.put("bookthief", 0);
	won.put("secretlifeofwaltermitty", 0);
	won.put("thirdperson", 0);
	won.put("diana", 0);
	won.put("fifstate", 0);
	won.put("counselor", 0);
	won.put("blueiswarmestcolor", 0);
	won.put("unfinishedsong", 0);
	won.put("aintmbodiessaints", 0);
	won.put("oldboy", 0);
	won.put("serena", 0);
	won.put("immigrant", 0);
	won.put("past", 0);
	won.put("shortterm12", 0);
	won.put("enoughsaid", 0);
	won.put("outoffurnace", 0);
	won.put("blingring", 0);
	won.put("monumentman", 0);
	won.put("spectacularnow", 0);
	won.put("fruitvale", 0);
	won.put("ironlady", 1);
	won.put("myweekwithmarilyn", 0);
	won.put("girlwiththedragontattoo", 1);
	won.put("beginners", 1);
	won.put("warrior10", 0);
	won.put("intothewoods", 0);
	won.put("faultinourstars", 0);
	won.put("kingsspeech", 1);
	won.put("socialnetwork", 1);
	won.put("truegrit2010", 0);
	won.put("blackswan", 1);
	won.put("fighter10", 1);
	won.put("inception", 1);
	won.put("127hours", 0);
	won.put("toystory3", 0);
	won.put("wintersbone", 0);
	won.put("kidsareallright", 0);
	won.put("biutiful", 0);
	won.put("rabbithole", 0);
	won.put("bluevalentine", 0);
	won.put("town10", 0);
	won.put("animalkingdom", 0);
	won.put("gusvansant", 0);

	oscars = new HashMap<String, Integer>();
	oscars.put("artist", 4);
	oscars.put("descendents", 1);
	oscars.put("help2011", 1);
	oscars.put("hugocabret", 4);
	oscars.put("warhorse", 0);
	oscars.put("extremelyloud", 0);
	oscars.put("treeoflife", 0);
	oscars.put("midnightinparis", 1);
	oscars.put("moneyball", 0);
	oscars.put("tinkertailorsoldierspy", 0);
	oscars.put("abetterlife", 0);
	oscars.put("wiigapatow", 0);
	oscars.put("albertnobbs", 0);
	oscars.put("lincoln", 1);
	oscars.put("themaster", 0);
	oscars.put("djangounchained", 2);
	oscars.put("silverliningsplaybook", 1);
	oscars.put("argo", 3);
	oscars.put("lifeofpi", 3);
	oscars.put("amour", 1);
	oscars.put("beastsofthesouthernwild", 0);
	oscars.put("lesmiserables2012", 1);
	oscars.put("flight", 0);
	oscars.put("binladen", 1);
	oscars.put("impossible", 0);
	oscars.put("hitchcock", 0);
	oscars.put("twelveyearsaslave", 3);
	oscars.put("gravity", 5);
	oscars.put("davido2013", 0);
	oscars.put("avengers11", 0);
	oscars.put("wolfofwallstreet", 0);
	oscars.put("captainphillips", 0);
	oscars.put("nebraska", 0);
	oscars.put("her2013", 1);
	oscars.put("philomena", 0);
	oscars.put("sixsessions", 0);
	oscars.put("bluejasmine", 1);
	oscars.put("dallasbuyersclub", 2);
	oscars.put("bestexoticmarigoldhotel", 0);
	oscars.put("brave", 0);
	oscars.put("augustosagecounty", 0);
	oscars.put("birdman", 4);
	oscars.put("boyhood", 1);
	oscars.put("imitationgame", 1);
	oscars.put("grandbudapesthotel", 1);
	oscars.put("americansniper", 1);
	oscars.put("whiplash", 2);
	oscars.put("theoryofeverything", 1);
	oscars.put("selma", 0);
	oscars.put("foxcatcher", 0);
	oscars.put("stillalice", 1);
	oscars.put("gonegirl", 0);
	oscars.put("wild2014", 0);
	oscars.put("twodaysonenight", 0);
	oscars.put("judge", 0);
	oscars.put("troubewiththecurve", 0);
	oscars.put("cloudatlas", 0);
	oscars.put("darkknightrises", 0);
	oscars.put("insidellewyndavis", 0);
	oscars.put("greatgatsby", 1);
	oscars.put("moonrisekingdom", 0);
	oscars.put("bond23", 1);
	oscars.put("hobbit", 0);
	oscars.put("annakarenina2012", 1);
	oscars.put("hydeparkonhudson", 0);
	oscars.put("killingthemsoftly", 0);
	oscars.put("placebeyondthepines", 0);
	oscars.put("darkknight", 2);
	oscars.put("promisedland", 0);
	oscars.put("rustandbone", 0);
	oscars.put("teleportationaccident", 0);
	oscars.put("sapphires", 0);
	oscars.put("trumbo", 0);
	oscars.put("suffragette", 0);
	oscars.put("spotlight", 0);
	oscars.put("youth", 0);
	oscars.put("bridgeofspies", 0);
	oscars.put("blackmass", 0);
	oscars.put("brooklyn", 0);
	oscars.put("nedbeauman", 0);
	oscars.put("placebeyondpines", 0);
	oscars.put("troublewithcurve", 0);
	oscars.put("killingmsoftly", 0);
	oscars.put("benhzeitlin", 0);
	oscars.put("arbitrage", 0);
	oscars.put("hopesprings", 0);
	oscars.put("endofwatch", 0);
	oscars.put("reseraquin", 0);
	oscars.put("wontbackdown", 0);
	oscars.put("smashed", 0);
	oscars.put("quartet", 0);
	oscars.put("francesha", 0);
	oscars.put("savingmrbanks", 0);
	oscars.put("mandela:longwalktofreedom", 0);
	oscars.put("blueiswarmestcolour", 0);
	oscars.put("butler", 0);
	oscars.put("allislost", 0);
	oscars.put("laborday", 0);
	oscars.put("monumentsmen", 0);
	oscars.put("beforemidnight", 0);
	oscars.put("fruitvalestation", 0);
	oscars.put("graceofmonaco", 0);
	oscars.put("mud", 0);
	oscars.put("rush", 0);
	oscars.put("bookthief", 0);
	oscars.put("secretlifeofwaltermitty", 0);
	oscars.put("thirdperson", 0);
	oscars.put("diana", 0);
	oscars.put("fifstate", 0);
	oscars.put("counselor", 0);
	oscars.put("blueiswarmestcolor", 0);
	oscars.put("unfinishedsong", 0);
	oscars.put("aintmbodiessaints", 0);
	oscars.put("oldboy", 0);
	oscars.put("serena", 0);
	oscars.put("immigrant", 0);
	oscars.put("past", 0);
	oscars.put("shortterm12", 0);
	oscars.put("enoughsaid", 0);
	oscars.put("outoffurnace", 0);
	oscars.put("blingring", 0);
	oscars.put("monumentman", 0);
	oscars.put("spectacularnow", 0);
	oscars.put("fruitvale", 0);
	oscars.put("ironlady", 2);
	oscars.put("myweekwithmarilyn", 0);
	oscars.put("girlwiththedragontattoo", 1);
	oscars.put("beginners", 1);
	oscars.put("warrior10", 0);
	oscars.put("intothewoods", 0);
	oscars.put("faultinourstars", 0);
	oscars.put("kingsspeech", 4);
	oscars.put("socialnetwork", 2);
	oscars.put("truegrit2010", 0);
	oscars.put("blackswan", 1);
	oscars.put("fighter10", 2);
	oscars.put("inception", 3);
	oscars.put("127hours", 0);
	oscars.put("toystory3", 0);
	oscars.put("wintersbone", 0);
	oscars.put("kidsareallright", 0);
	oscars.put("biutiful", 0);
	oscars.put("rabbithole", 0);
	oscars.put("bluevalentine", 0);
	oscars.put("town10", 0);
	oscars.put("animalkingdom", 0);
	oscars.put("gusvansant", 0);

	nominations = new HashMap<String, Integer>();
	nominations.put("artist", 9);
	nominations.put("descendents", 5);
	nominations.put("help2011", 4);
	nominations.put("hugocabret", 9);
	nominations.put("warhorse", 4);
	nominations.put("extremelyloud", 2);
	nominations.put("treeoflife", 3);
	nominations.put("midnightinparis", 4);
	nominations.put("moneyball", 5);
	nominations.put("tinkertailorsoldierspy", 2);
	nominations.put("abetterlife", 1);
	nominations.put("wiigapatow", 2);
	nominations.put("albertnobbs", 3);
	nominations.put("lincoln", 9);
	nominations.put("themaster", 3);
	nominations.put("djangounchained", 5);
	nominations.put("silverliningsplaybook", 8);
	nominations.put("argo", 5);
	nominations.put("lifeofpi", 7);
	nominations.put("amour", 5);
	nominations.put("beastsofthesouthernwild", 4);
	nominations.put("lesmiserables2012", 4);
	nominations.put("flight", 2);
	nominations.put("binladen", 5);
	nominations.put("impossible", 1);
	nominations.put("hitchcock", 0);
	nominations.put("twelveyearsaslave", 8);
	nominations.put("gravity", 7);
	nominations.put("davido2013", 9);
	nominations.put("avengers11", 1);
	nominations.put("wolfofwallstreet", 5);
	nominations.put("captainphillips", 5);
	nominations.put("nebraska", 6);
	nominations.put("her2013", 2);
	nominations.put("philomena", 3);
	nominations.put("sixsessions", 1);
	nominations.put("bluejasmine", 3);
	nominations.put("dallasbuyersclub", 5);
	nominations.put("bestexoticmarigoldhotel", 0);
	nominations.put("brave", 0);
	nominations.put("augustosagecounty", 2);
	nominations.put("birdman", 8);
	nominations.put("boyhood", 6);
	nominations.put("imitationgame", 6);
	nominations.put("grandbudapesthotel", 6);
	nominations.put("americansniper", 5);
	nominations.put("whiplash", 4);
	nominations.put("theoryofeverything", 4);
	nominations.put("selma", 1);
	nominations.put("foxcatcher", 4);
	nominations.put("stillalice", 1);
	nominations.put("gonegirl", 1);
	nominations.put("wild2014", 2);
	nominations.put("twodaysonenight", 1);
	nominations.put("judge", 1);
	nominations.put("troubewiththecurve", 0);
	nominations.put("cloudatlas", 0);
	nominations.put("darkknightrises", 0);
	nominations.put("insidellewyndavis", 1);
	nominations.put("greatgatsby", 1);
	nominations.put("moonrisekingdom", 1);
	nominations.put("bond23", 2);
	nominations.put("hobbit", 1);
	nominations.put("annakarenina2012", 2);
	nominations.put("hydeparkonhudson", 0);
	nominations.put("killingthemsoftly", 0);
	nominations.put("placebeyondthepines", 0);
	nominations.put("darkknight", 7);
	nominations.put("promisedland", 0);
	nominations.put("rustandbone", 0);
	nominations.put("teleportationaccident", 0);
	nominations.put("sapphires", 0);
	nominations.put("trumbo", 0);
	nominations.put("suffragette", 0);
	nominations.put("spotlight", 0);
	nominations.put("youth", 0);
	nominations.put("bridgeofspies", 0);
	nominations.put("blackmass", 0);
	nominations.put("brooklyn", 0);
	nominations.put("nedbeauman", 0);
	nominations.put("placebeyondpines", 0);
	nominations.put("troublewithcurve", 0);
	nominations.put("killingmsoftly", 0);
	nominations.put("benhzeitlin", 0);
	nominations.put("arbitrage", 0);
	nominations.put("hopesprings", 0);
	nominations.put("endofwatch", 0);
	nominations.put("reseraquin", 0);
	nominations.put("wontbackdown", 0);
	nominations.put("smashed", 0);
	nominations.put("quartet", 0);
	nominations.put("francesha", 0);
	nominations.put("savingmrbanks", 0);
	nominations.put("mandela:longwalktofreedom", 0);
	nominations.put("blueiswarmestcolour", 0);
	nominations.put("butler", 0);
	nominations.put("allislost", 1);
	nominations.put("laborday", 0);
	nominations.put("monumentsmen", 0);
	nominations.put("beforemidnight", 1);
	nominations.put("fruitvalestation", 0);
	nominations.put("graceofmonaco", 0);
	nominations.put("mud", 0);
	nominations.put("rush", 0);
	nominations.put("bookthief", 0);
	nominations.put("secretlifeofwaltermitty", 0);
	nominations.put("thirdperson", 0);
	nominations.put("diana", 0);
	nominations.put("fifstate", 0);
	nominations.put("counselor", 0);
	nominations.put("blueiswarmestcolor", 0);
	nominations.put("unfinishedsong", 0);
	nominations.put("aintmbodiessaints", 0);
	nominations.put("oldboy", 0);
	nominations.put("serena", 0);
	nominations.put("immigrant", 0);
	nominations.put("past", 0);
	nominations.put("shortterm12", 0);
	nominations.put("enoughsaid", 0);
	nominations.put("outoffurnace", 0);
	nominations.put("blingring", 0);
	nominations.put("monumentman", 0);
	nominations.put("spectacularnow", 0);
	nominations.put("fruitvale", 0);
	nominations.put("ironlady", 2);
	nominations.put("myweekwithmarilyn", 2);
	nominations.put("girlwiththedragontattoo", 4);
	nominations.put("beginners", 1);
	nominations.put("warrior10", 1);
	nominations.put("intothewoods", 2);
	nominations.put("faultinourstars", 0);
	nominations.put("kingsspeech", 10);
	nominations.put("socialnetwork", 6);
	nominations.put("truegrit2010", 9);
	nominations.put("blackswan", 5);
	nominations.put("fighter10", 7);
	nominations.put("inception", 6);
	nominations.put("127hours", 4);
	nominations.put("toystory3", 3);
	nominations.put("wintersbone", 4);
	nominations.put("kidsareallright", 4);
	nominations.put("biutiful", 2);
	nominations.put("rabbithole", 1);
	nominations.put("bluevalentine", 1);
	nominations.put("town10", 1);
	nominations.put("animalkingdom", 1);
	nominations.put("gusvansant", 0);
    }

    private MongoClient mongoClient;
    private MongoCredential mongoCredential;
    private DB db;

    public MongoDBHandler(String host, String port, String userName,
	    String database, String password) {
	this.mongoCredential = MongoCredential.createCredential(userName,
		database, password.toCharArray());

	this.mongoClient = new MongoClient(new ServerAddress(host),
		Arrays.asList(mongoCredential));
	this.db = mongoClient.getDB(database);
    }

    public void storeData(JSONObject data, String collection) {
	DBObject dbObject = (DBObject) JSON.parse(data.toJSONString());
	DBCollection dbCollection = db.getCollection(collection);
	dbCollection.insert(dbObject);

    }

    @SuppressWarnings("unchecked")
    public void rewriteData(String collection) {

	DBCollection dbCollection = db.getCollection(collection);

	DBCursor cursor = dbCollection.find();
	int index = 0;
	int mongoEntries = 0;
	try {
	    while (cursor.hasNext()) {
		JSONObject newJsonObject = new JSONObject();
		JSONObject jsonObject = new JSONObject(cursor.next().toMap());
		BasicDBList itemList = (BasicDBList) jsonObject.get("data");
		for (int i = 0; i < itemList.size(); i++) {
		    BasicDBObject itemContent = (BasicDBObject) itemList.get(i);

		    // System.out.println(itemContent.get("name").toString());

		    Collection<Entry<String, Object>> items = itemContent
			    .entrySet();
		    List<Float> bettingOdds = new ArrayList<Float>();

		    for (Entry<String, Object> itemEntry : items) {

			String itemString = (String) itemEntry.getValue();
			Float value = 0f;
			if (itemEntry.getKey().equals("SI")) {
			    // omitting SI due to odds-format
			} else if (itemString.contains("/")
				&& !itemEntry.getKey().equals("name")
				&& !itemEntry.getKey().equals("id")
				&& !itemString.contains("<script")
				&& !itemString.contains("<span")) {
			    float numerator = Float.parseFloat(itemString
				    .substring(0, itemString.indexOf("/")));
			    float denominator = Float.parseFloat(itemString
				    .substring(itemString.indexOf("/") + 1));
			    float probability = denominator
				    / (numerator + denominator);
			    value = Float.valueOf(probability);
			    bettingOdds.add(value);

			    if (itemEntry.getKey().equals("PP")
				    || itemEntry.getKey().equals("LD")) {
				newJsonObject.put(itemEntry.getKey(), value);
			    }

			} else if (itemString.matches("[0-9]+")
				&& (!itemEntry.getKey().equals("id") || !itemEntry
					.getKey().equals("betting_id"))) {
			    float probability = 1f / (Float
				    .parseFloat(itemString) + 1f);
			    value = Float.valueOf(probability);
			    bettingOdds.add(value);

			    if (itemEntry.getKey().equals("PP")
				    || itemEntry.getKey().equals("LD")) {
				newJsonObject.put(itemEntry.getKey(), value);
			    }

			} else if (itemEntry.getKey().equals("name")) {
			    String name = itemContent.get("name").toString();
			    name = name.replaceAll("  ", " ");

			    if (name.contains("(")) {
				name = name.replaceAll("[(]", "- ");
				name = name.replaceAll("[)]", "");
			    }
			    newJsonObject.put("name", itemEntry.getValue());
			}

			index++;
		    }

		    String name = itemContent.get("name").toString();
		    // String movieName = getMovieName(name);
		    // boxOfficeIds.put("Ang Lee (Life Of Pi)", "lifeofpi");

		    name = name.replaceAll("  ", " ");

		    if (name.contains("(")) {
			name = name.replaceAll("[(]", "- ");
			name = name.replaceAll("[)]", "");
		    }

		    newJsonObject.put("boxOfficeId", boxOfficeIds.get(name));
		    newJsonObject.put("timestamp", jsonObject.get("timestamp"));
		    newJsonObject.put("category", jsonObject.get("category"));

		    String ts = jsonObject.get("timestamp").toString();
		    ts = ts.replaceAll(":| |-|\\.", "");

		    Float bettingOddsSUM = 0f;
		    Float bettingOddsAVG = 0f;
		    Float bettingOddsMIN = 1f;
		    Float bettingOddsMAX = 0f;
		    for (int j = 0; j < bettingOdds.size(); j++) {
			bettingOddsSUM += bettingOdds.get(j);
			if (bettingOdds.get(j) < bettingOddsMIN) {
			    bettingOddsMIN = bettingOdds.get(j);
			}
			if (bettingOdds.get(j) > bettingOddsMAX) {
			    bettingOddsMAX = bettingOdds.get(j);
			}
		    }
		    bettingOddsAVG = bettingOddsSUM / bettingOdds.size();

		    newJsonObject.put("mean", bettingOddsAVG.floatValue());
		    newJsonObject.put("min", bettingOddsMIN.floatValue());
		    newJsonObject.put("max", bettingOddsMAX.floatValue());
		    newJsonObject.put("count", bettingOdds.size());

		    newJsonObject.put("won", won.get(boxOfficeIds.get(name)));
		    newJsonObject.put("oscars",
			    oscars.get(boxOfficeIds.get(name)));
		    newJsonObject.put("nominations",
			    nominations.get(boxOfficeIds.get(name)));

		    newJsonObject.put("_id",
			    ts + index + boxOfficeIds.get(name));
		    if (newJsonObject.get("boxOfficeId") != null) {
			storeData(newJsonObject, "bettingOdds_formatted");
		    } else {
			storeData(newJsonObject, "bettingOdds_formatted");
			System.out.println("("
				+ jsonObject.get("timestamp").toString()
					.substring(0, 4) + ") " + name);
		    }

		}
		mongoEntries++;
	    }
	} finally {
	    cursor.close();
	}

	System.out.println("processed mongoEntries: " + mongoEntries);
	System.out.println("result lines: " + index);
    }

    private String getMovieName(String name) {
	if (name.contains(" - ")) {
	    name = name.substring(name.indexOf(" - ") + 3);
	}

	return name;
    }
}
