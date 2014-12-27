package org.jzy3d.tests;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.ChartLauncher;
import org.jzy3d.chart.factories.AxeTransformableAWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.axeTransformable.axeTransformableBuilder;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.CompileableComposite;
import org.jzy3d.plot3d.primitives.axeTransformablePrimitive.AxeTransformablePoint;
import org.jzy3d.plot3d.primitives.axeTransformablePrimitive.axeTransformers.AxeTransformerSet;
import org.jzy3d.plot3d.primitives.axeTransformablePrimitive.axeTransformers.LinearAxeTransformer;
import org.jzy3d.plot3d.primitives.axeTransformablePrimitive.axeTransformers.LogAxeTransformer;
import org.jzy3d.plot3d.rendering.canvas.Quality;


public class LogTest {

	public static void main(String[] args) {
		// Define a function to plot
		Mapper mapper = new Mapper() {
		    public double f(double x, double y) {
		        double value =  Math.abs((10 * Math.sin(x) * Math.cos(y) * x) / 10) + 10;
		        return value;
		    }
		};

		// Define range and precision for the function to plot
		Range range = new Range((float)0.1, 10);
		Range range2 = new Range((float)0.1,50);
		int steps = 200;

		AxeTransformerSet transformers = new AxeTransformerSet(new LogAxeTransformer(), new LinearAxeTransformer(), new LinearAxeTransformer());
		
		// Create a surface drawing that function
		CompileableComposite surface = axeTransformableBuilder.buildOrthonormalBig(new OrthonormalGrid(range, steps, range2, steps), mapper, transformers);
		surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
		surface.setFaceDisplayed(true);
		surface.setWireframeDisplayed(false);
		surface.setWireframeColor(Color.BLACK);
		
		/*AxeTransformableCylinder cyl = new AxeTransformableCylinder(transformers);
		cyl.setData(new Coord3d(10,10,0), 10, 3, 20, 20, new Color(1, 0, 0));*/
		
		/*AxeTransformableFlatLine2d fline = new AxeTransformableFlatLine2d(new float[]{1.f,2.f,5.f}, new float[]{3.f,1.f,0.2f}, 5,transformers);
		fline.setWireframeColor(new Color(1,1,1));*/
		
		/*ArrayList<Coord3d> points = new ArrayList<Coord3d>();
		points.add(new Coord3d(0,0,0));
		points.add(new Coord3d(1,1,1));
		points.add(new Coord3d(3,3,1));
		
		AxeTransformableLineStripInterpolated line = new AxeTransformableLineStripInterpolated(new BernsteinInterpolator(), points, 100, transformers);
		line.setWireframeColor(new Color(1,1,1));*/
		
		AxeTransformablePoint point1 = new AxeTransformablePoint(transformers);
		point1.setData(new Coord3d(1,1,1));
		point1.setColor(new Color(1, 0, 0));
		point1.setWidth(10);
		AxeTransformablePoint point2 = new AxeTransformablePoint(transformers);
		point2.setData(new Coord3d(2,3,3));
		point2.setColor(new Color(0,1,0));
		point2.setWidth(10);
		AxeTransformablePoint point3 = new AxeTransformablePoint(transformers);
		point3.setData(new Coord3d(3,4,2));
		point3.setColor(new Color(0,0,1));
		point3.setWidth(10);
				

		// Create a chart and add the surface
		Chart chart = AxeTransformableAWTChartComponentFactory.chart(Quality.Advanced, "awt", transformers);
		chart.addDrawable(point1);
		chart.addDrawable(point2);
		chart.addDrawable(point3);
		chart.addDrawable(surface);
		chart.getView().setTransformers(transformers);
		ChartLauncher.openChart(chart);
	}
}
