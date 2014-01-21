package vggames.shared.view

import scalatags._

class Sitemap extends TypedView[(List[String], String)] {

  override def renderString(t : (List[String], String)) = {
    val (games, buildDate) = t

    (
      <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
        <url>
          <loc>http://games.vidageek.net/</loc>
          <lastmod>{ buildDate }</lastmod>
          <changefreq>weekly</changefreq>
          <priority>0.1</priority>
        </url>
        {
          games.map { game =>
            <url>
              <loc>http://games.vidageek.net/play/{ game }</loc>
              <lastmod>{ buildDate }</lastmod>
              <changefreq>weekly</changefreq>
              <priority>0.5</priority>
            </url>

          }
        }
      </urlset>).toString

  }

  override def contentType = "application/xml; charset=UTF-8"
}