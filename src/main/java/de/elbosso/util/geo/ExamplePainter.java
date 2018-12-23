/*
Copyright (c) 2012-2018.

Juergen Key. Alle Rechte vorbehalten.

Weiterverbreitung und Verwendung in nichtkompilierter oder kompilierter Form,
mit oder ohne Veraenderung, sind unter den folgenden Bedingungen zulaessig:

   1. Weiterverbreitete nichtkompilierte Exemplare muessen das obige Copyright,
die Liste der Bedingungen und den folgenden Haftungsausschluss im Quelltext
enthalten.
   2. Weiterverbreitete kompilierte Exemplare muessen das obige Copyright,
die Liste der Bedingungen und den folgenden Haftungsausschluss in der
Dokumentation und/oder anderen Materialien, die mit dem Exemplar verbreitet
werden, enthalten.
   3. Weder der Name des Autors noch die Namen der Beitragsleistenden
duerfen zum Kennzeichnen oder Bewerben von Produkten, die von dieser Software
abgeleitet wurden, ohne spezielle vorherige schriftliche Genehmigung verwendet
werden.

DIESE SOFTWARE WIRD VOM AUTOR UND DEN BEITRAGSLEISTENDEN OHNE
JEGLICHE SPEZIELLE ODER IMPLIZIERTE GARANTIEN ZUR VERFUEGUNG GESTELLT, DIE
UNTER ANDEREM EINSCHLIESSEN: DIE IMPLIZIERTE GARANTIE DER VERWENDBARKEIT DER
SOFTWARE FUER EINEN BESTIMMTEN ZWECK. AUF KEINEN FALL IST DER AUTOR
ODER DIE BEITRAGSLEISTENDEN FUER IRGENDWELCHE DIREKTEN, INDIREKTEN,
ZUFAELLIGEN, SPEZIELLEN, BEISPIELHAFTEN ODER FOLGENDEN SCHAEDEN (UNTER ANDEREM
VERSCHAFFEN VON ERSATZGUETERN ODER -DIENSTLEISTUNGEN; EINSCHRAENKUNG DER
NUTZUNGSFAEHIGKEIT; VERLUST VON NUTZUNGSFAEHIGKEIT; DATEN; PROFIT ODER
GESCHAEFTSUNTERBRECHUNG), WIE AUCH IMMER VERURSACHT UND UNTER WELCHER
VERPFLICHTUNG AUCH IMMER, OB IN VERTRAG, STRIKTER VERPFLICHTUNG ODER
UNERLAUBTE HANDLUNG (INKLUSIVE FAHRLAESSIGKEIT) VERANTWORTLICH, AUF WELCHEM
WEG SIE AUCH IMMER DURCH DIE BENUTZUNG DIESER SOFTWARE ENTSTANDEN SIND, SOGAR,
WENN SIE AUF DIE MOEGLICHKEIT EINES SOLCHEN SCHADENS HINGEWIESEN WORDEN SIND.

 */

package de.elbosso.util.geo;

import de.netsysit.util.geo.Map;
import de.netsysit.util.geo.mashups.POI;
import java.awt.Graphics2D;
import java.util.List;

public class ExamplePainter extends de.netsysit.util.geo.mashups.Painter
{

	public ExamplePainter(de.netsysit.ui.geo.MapView mapView)
	{
		super(mapView);
	}

	@Override
	public void paint(Graphics2D ig, Map map)
	{
		if(isEnabled())
		{
			ig.setPaint(java.awt.Color.red);
			java.awt.Rectangle rect=map.getTiles();
			int xloc=map.getReferenceOffset().x;
			for(int x=0;x<rect.width;++x)
			{
				int yloc=map.getReferenceOffset().y;
				for(int y=0;y<rect.height;++y)
				{
					int checkerx=rect.x+x;
					int checkery=rect.y+y;
					if(checkerx%2==checkery%2)
					{
						ig.setColor(new java.awt.Color(255,255,255,40));
					}
					else
					{
						ig.setColor(new java.awt.Color(0,0,0,40));
					}
					ig.fillRect(xloc, yloc, 256, 256);
					java.awt.Font f=new java.awt.Font("dialog", java.awt.Font.PLAIN,12);
					ig.setFont(f);
					java.awt.FontMetrics fm=ig.getFontMetrics(f);
					java.lang.String s="/"+map.getZoomLevel()+"/"+checkerx+"/"+checkery;
					java.awt.font.LineMetrics lm=fm.getLineMetrics(s, ig);
					int h=(int)lm.getHeight();
					int w=fm.stringWidth(s);
					ig.setColor(new java.awt.Color(0x80ffffff, true));
					ig.fillRect(xloc+256-w-5, yloc+256-h-5, w, h);
					ig.setPaint(java.awt.Color.red);
					ig.drawString(s, xloc+256-w-5, yloc+256-h-5+lm.getAscent());
					yloc+=256;
				}
				xloc+=256;
			}
		}
	}

	@Override
	public boolean doubleClicked()
	{
		return false;
	}

	@Override
	public boolean assessMustRepaint(List<? extends POI> l, boolean mustrepaint)
	{
		return mustrepaint;
	}

	@Override
	public void sort(List<? extends POI> l)
	{

	}

	@Override
	protected void mouseMatch(POI o1)
	{

	}

}
