/*
 * Alfred Library.
 * Copyright (C) 2011 Alfred Team
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.alfredlibrary.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper to work in texts containing HTML markup.
 * 
 * @author Marlon Silva Carvalho
 * @since 2.0.0
 */
final public class HTML {

	/**
	 * Find all link contained in a Text and return it in a array.
	 * 
	 * @param text Text containing the links.
	 * @return Array containing all links found.
	 */
	public static String[] findLinks(String text) {
		Collection<String> found = new ArrayList<String>();
		String strPattern = "https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?";
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			found.add(matcher.group());
		}
		return found.toArray(new String[] {});
	}

	/**
	 * Remove all tags found in the text.
	 * 
	 * @param text Text to search for tags.
	 * @return Text with no tags.
	 */
	public static String removeTags(String text) {
		String noHTMLString = text.replaceAll("\\<.*?\\>", "");
		return noHTMLString;
	}

	/**
	 * Replace all special HTML entities, like &nbsp;, by the corresponding ASCII entity.
	 * Original code from http://www.rgagnon.com/javadetails/java-0307.html.
	 * 
	 * @param source Text to be replaced.
	 * @param start Start.
	 * @return Replaced text.
	 */
	public static String decodeSpecialHTMLEntities(String source, int start) {
		HashMap<String, String> htmlEntities;
		htmlEntities = new HashMap<String, String>();
		htmlEntities.put("&lt;", "<");
		htmlEntities.put("&gt;", ">");
		htmlEntities.put("&amp;", "&");
		htmlEntities.put("&quot;", "\"");
		htmlEntities.put("&agrave;", "à");
		htmlEntities.put("&Agrave;", "À");
		htmlEntities.put("&atilde;", "ã");
		htmlEntities.put("&Atilde;", "Ã");
		htmlEntities.put("&aacute;", "á");
		htmlEntities.put("&Aacute;", "Á");
		htmlEntities.put("&acirc;", "â");
		htmlEntities.put("&auml;", "ä");
		htmlEntities.put("&Auml;", "Ä");
		htmlEntities.put("&Acirc;", "Â");
		htmlEntities.put("&aring;", "å");
		htmlEntities.put("&Aring;", "Å");
		htmlEntities.put("&aelig;", "æ");
		htmlEntities.put("&AElig;", "Æ");
		htmlEntities.put("&ccedil;", "ç");
		htmlEntities.put("&Ccedil;", "Ç");
		htmlEntities.put("&eacute;", "é");
		htmlEntities.put("&Eacute;", "É");
		htmlEntities.put("&egrave;", "è");
		htmlEntities.put("&Egrave;", "È");
		htmlEntities.put("&ecirc;", "ê");
		htmlEntities.put("&Ecirc;", "Ê");
		htmlEntities.put("&euml;", "ë");
		htmlEntities.put("&Euml;", "Ë");
		htmlEntities.put("&iuml;", "ï");
		htmlEntities.put("&Iuml;", "Ï");
		htmlEntities.put("&iacute;", "í");
		htmlEntities.put("&Iacute;", "Í");
		htmlEntities.put("&ocirc;", "ô");
		htmlEntities.put("&Ocirc;", "Ô");
		htmlEntities.put("&otilde;", "õ");
		htmlEntities.put("&Otilde;", "Õ");
		htmlEntities.put("&oacute;", "ó");
		htmlEntities.put("&Oacute;", "Ó");
		htmlEntities.put("&uacute;", "ú");
		htmlEntities.put("&Uacute;", "Ú");
		htmlEntities.put("&ouml;", "ö");
		htmlEntities.put("&Ouml;", "Ö");
		htmlEntities.put("&oslash;", "ø");
		htmlEntities.put("&Oslash;", "Ø");
		htmlEntities.put("&szlig;", "ß");
		htmlEntities.put("&ugrave;", "ù");
		htmlEntities.put("&Ugrave;", "Ù");
		htmlEntities.put("&ucirc;", "û");
		htmlEntities.put("&Ucirc;", "Û");
		htmlEntities.put("&uuml;", "ü");
		htmlEntities.put("&Uuml;", "Ü");
		htmlEntities.put("&nbsp;", " ");
		htmlEntities.put("&copy;", "\u00a9");
		htmlEntities.put("&reg;", "\u00ae");
		htmlEntities.put("&euro;", "\u20a0");
		int i, j;
		i = source.indexOf("&", start);
		if (i > -1) {
			j = source.indexOf(";", i);
			if (j > i) {
				String entityToLookFor = source.substring(i, j + 1);
				String value = (String) htmlEntities.get(entityToLookFor);
				if (value != null) {
					source = new StringBuffer().append(source.substring(0, i))
							.append(value).append(source.substring(j + 1))
							.toString();
				}
				return decodeSpecialHTMLEntities(source, i + 1);
			}
		}
		return source;
	}

	/**
	 * Converte caracteres especiais para elementos HTML. Código "gentilmente sugado" do site
	 * 
	 * @param source Text to be replaced.
	 * @return Replaced text.
	 */
	public static final String encodeToSpecialHTMLEntities(String source) {
		HashMap<Character, String> htmlEntities;
		htmlEntities = new HashMap<Character, String>();
		htmlEntities.put('<', "&lt;");
		htmlEntities.put('>', "&gt;");
		htmlEntities.put('&', "&amp;");
		htmlEntities.put('\\', "&quot;");
		htmlEntities.put('à', "&agrave;");
		htmlEntities.put('À', "&Agrave;");
		htmlEntities.put('ã', "&atilde;");
		htmlEntities.put('Ã', "&Atilde;");
		htmlEntities.put('á', "&aacute;");
		htmlEntities.put('Á', "&Aacute;");
		htmlEntities.put('â', "&acirc;");
		htmlEntities.put('ä', "&auml;");
		htmlEntities.put('Ä', "&Auml;");
		htmlEntities.put('Â', "&Acirc;");
		htmlEntities.put('å', "&aring;");
		htmlEntities.put('Å', "&Aring;");
		htmlEntities.put('æ', "&aelig;");
		htmlEntities.put('Æ', "&AElig;");
		htmlEntities.put('ç', "&ccedil;");
		htmlEntities.put('Ç', "&Ccedil;");
		htmlEntities.put('é', "&eacute;");
		htmlEntities.put('É', "&Eacute;");
		htmlEntities.put('è', "&egrave;");
		htmlEntities.put('È', "&Egrave;");
		htmlEntities.put('ê', "&ecirc;");
		htmlEntities.put('Ê', "&Ecirc;");
		htmlEntities.put('ë', "&euml;");
		htmlEntities.put('Ë', "&Euml;");
		htmlEntities.put('ï', "&iuml;");
		htmlEntities.put('Ï', "&Iuml;");
		htmlEntities.put('í', "&iacute;");
		htmlEntities.put('Í', "&Iacute;");
		htmlEntities.put('ô', "&ocirc;");
		htmlEntities.put('Ô', "&Ocirc;");
		htmlEntities.put('õ', "&otilde;");
		htmlEntities.put('Õ', "&Otilde;");
		htmlEntities.put('ó', "&oacute;");
		htmlEntities.put('Ó', "&Oacute;");
		htmlEntities.put('ú', "&uacute;");
		htmlEntities.put('Ú', "&Uacute;");
		htmlEntities.put('ö', "&ouml;");
		htmlEntities.put('Ö', "&Ouml;");
		htmlEntities.put('ø', "&oslash;");
		htmlEntities.put('Ø', "&Oslash;");
		htmlEntities.put('ß', "&szlig;");
		htmlEntities.put('ù', "&ugrave;");
		htmlEntities.put('Ù', "&Ugrave;");
		htmlEntities.put('û', "&ucirc;");
		htmlEntities.put('Û', "&Ucirc;");
		htmlEntities.put('ü', "&uuml;");
		htmlEntities.put('Ü', "&Uuml;");
		htmlEntities.put(' ', "&nbsp;");
		htmlEntities.put('\u00a9', "&copy;");
		htmlEntities.put('\u00ae', "&reg;");
		htmlEntities.put('\u20a0', "&euro;");
		int length = source.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < length; i++) {
			char ch = source.charAt(i);
			if ( htmlEntities.containsKey(ch) ) {
				String o = htmlEntities.get(ch);
				sb.append(o);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

}
