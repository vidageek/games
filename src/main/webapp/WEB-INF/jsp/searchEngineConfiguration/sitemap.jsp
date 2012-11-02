<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="application/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	<url>
		<loc>/</loc>
		<lastmod>${buildDate}</lastmod>
		<changefreq>weekly</changefreq>
		<priority>0.5</priority>	
	</url>
	<c:forEach items="${games}" var="game">
	<url>
		<loc>/play/${game}</loc>
		<lastmod>${buildDate}</lastmod>
		<changefreq>weekly</changefreq>
		<priority>1</priority>	
	</url>
	</c:forEach>
</urlset>