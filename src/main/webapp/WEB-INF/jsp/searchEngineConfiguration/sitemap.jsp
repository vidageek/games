<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="application/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	<url>
		<loc>http://games.vidageek.net/</loc>
		<lastmod>${buildDate}</lastmod>
		<changefreq>weekly</changefreq>
		<priority>0.1</priority>	
	</url>
	<c:forEach items="${games}" var="game">
	<url>
		<loc>http://games.vidageek.net/play/${game}</loc>
		<lastmod>${buildDate}</lastmod>
		<changefreq>weekly</changefreq>
		<priority>0.5</priority>	
	</url>
	</c:forEach>
	<c:forEach items="${games}" var="game">
	<url>
		<loc>http://games.vidageek.net/theory/${game}</loc>
		<lastmod>${buildDate}</lastmod>
		<changefreq>weekly</changefreq>
		<priority>1</priority>	
	</url>
	</c:forEach>
</urlset>