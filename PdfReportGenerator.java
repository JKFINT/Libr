package Libraries;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PdfReportGenerator extends Driver {

	public static void pdfReport() {

		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new CMYKColor(255, 0, 0, 0));
		Font headLine = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));

		// String fileName="C:/Users/jayakumarj/Desktop/Report/report.pdf";
		String fileName = bpath.get() + "/report/" + runid.get() + "/Report.pdf";
		String report = bpath.get() + "/report/" + runid.get() + "/Report.json";
		// String report="C:/Users/jayakumarj/Desktop/New.json";
		Document document = new Document();

		try {
			clog.info(fileName);
			// readJson(report);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;

			JSONArray totalCases = (JSONArray) parser.parse(new FileReader(report));

			int totalNumberOfCases = totalCases.size();
			int totalNumberOfPass = 0;
			int totalNumberOfFail = 0;
			int totalNumberOfSkip = 0;

			clog.info("array" + totalCases.size());

			for (int i = 0; i < totalCases.size(); i++) {

				jsonObject = (JSONObject) totalCases.get(i);

				String status = (String) jsonObject.get("status");

				if (status.equalsIgnoreCase("Pass")) {
					totalNumberOfPass = totalNumberOfPass + 1;
				} else if (status.equalsIgnoreCase("fail")) {
					totalNumberOfFail = totalNumberOfFail + 1;
				} else {
					totalNumberOfSkip = totalNumberOfSkip + 1;
				}
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();

			Paragraph paragraphOne = new Paragraph("OVER ALL TEST AUTOMATION REPORT" + "", headLine);

			paragraphOne.setAlignment(Element.ALIGN_CENTER);

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(50f); // Space before table
			table.setSpacingAfter(20f); // Space after table

			// Set Column widths
			float[] columnWidths = { 100f, 100f };
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("TOTAL CASES EXECUTED"));
			cell1.setBorderColor(BaseColor.BLACK);
			cell1.setPaddingLeft(100);
			cell1.setBackgroundColor(BaseColor.GRAY);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph(String.valueOf(totalNumberOfCases)));
			cell2.setBorderColor(BaseColor.BLACK);
			cell2.setPaddingLeft(10);
			cell2.setBackgroundColor(BaseColor.GRAY);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("PASSED"));
			cell3.setBorderColor(BaseColor.BLACK);
			cell3.setPaddingLeft(10);
			cell3.setBackgroundColor(BaseColor.WHITE);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell4 = new PdfPCell(new Paragraph(String.valueOf(totalNumberOfPass)));
			cell4.setBorderColor(BaseColor.BLACK);
			cell4.setPaddingLeft(10);
			cell4.setBackgroundColor(BaseColor.GREEN);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell5 = new PdfPCell(new Paragraph("FAILED"));
			cell5.setBorderColor(BaseColor.BLACK);
			cell5.setPaddingLeft(10);
			cell5.setBackgroundColor(BaseColor.WHITE);
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell6 = new PdfPCell(new Paragraph(String.valueOf(totalNumberOfFail)));
			cell6.setBorderColor(BaseColor.BLACK);
			cell6.setPaddingLeft(10);
			cell6.setBackgroundColor(BaseColor.RED);
			cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell7 = new PdfPCell(new Paragraph("SKIPPED"));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setPaddingLeft(10);
			cell7.setBackgroundColor(BaseColor.WHITE);
			cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell8 = new PdfPCell(new Paragraph(String.valueOf(totalNumberOfSkip)));
			cell8.setBorderColor(BaseColor.BLACK);
			cell8.setPaddingLeft(10);
			cell8.setBackgroundColor(BaseColor.ORANGE);
			cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// To avoid having the cell border and the content overlap, if you are having
			// thick cell borders

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);

			document.add(paragraphOne);

			// table.writeSelectedRows(0, -1, 50, 80, writer.getDirectContent());

			document.add(table);

			JFreeChart chart = generatePieChart1(totalNumberOfSkip, totalNumberOfPass, totalNumberOfFail);

			PdfContentByte pdfContentByte = writer.getDirectContent();
			int width = 400; // width of PieChart
			int height = 300; // height of pieChart
			PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);

			// create graphics
			Graphics2D graphics2d = pdfTemplate.createGraphics(width, height, new DefaultFontMapper());

			// create rectangle
			java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			pdfContentByte.addTemplate(pdfTemplate, 80, 300); // 0, 0 will draw PIE chart on bottom left of page

			PdfPTable table1 = new PdfPTable(4);

			table1.setWidthPercentage(50); // Width 100%
			table1.setSpacingBefore(50f); // Space before table
			table1.setSpacingAfter(20f); // Space after table

			PdfPCell header1 = new PdfPCell(new Paragraph("TEST CASE"));
			header1.setBorderColor(BaseColor.BLACK);
			header1.setPaddingLeft(10);
			header1.setBackgroundColor(BaseColor.GRAY);
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell header2 = new PdfPCell(new Paragraph("STATUS"));
			header2.setBorderColor(BaseColor.BLACK);
			header2.setPaddingLeft(10);
			header2.setBackgroundColor(BaseColor.GRAY);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell header3 = new PdfPCell(new Paragraph("Start Time"));
			header3.setBorderColor(BaseColor.BLACK);
			header3.setPaddingLeft(5);
			header3.setBackgroundColor(BaseColor.GRAY);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell header4 = new PdfPCell(new Paragraph("End Time"));
			header4.setBorderColor(BaseColor.BLACK);
			header4.setPaddingLeft(10);
			header4.setBackgroundColor(BaseColor.GRAY);
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setVerticalAlignment(Element.ALIGN_MIDDLE);

//			        PdfPCell header5 = new PdfPCell(new Paragraph("Test Steps"));
//			        header5.setBorderColor(BaseColor.BLACK);
//			        header5.setPaddingLeft(10);
//			        header5.setBackgroundColor(BaseColor.GRAY);
//			        header5.setHorizontalAlignment(Element.ALIGN_CENTER);
//			        header5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table1.addCell(header1);
			table1.addCell(header2);
			table1.addCell(header3);
			table1.addCell(header4);
			// table1.addCell(header5);

			for (int i = 0; i < totalCases.size(); i++) {

				jsonObject = (JSONObject) totalCases.get(i);

				String testCase = (String) jsonObject.get("Test Case ID");
				String status = (String) jsonObject.get("status");
				String Start = (String) jsonObject.get("Start Time");
				String end = (String) jsonObject.get("End Time");
				// String testCase = (String) jsonObject.get("Test Case ID");

				System.out.println("Test Case ID" + testCase);

				System.out.println("status" + status);
				System.out.println("Start Time" + Start);
				System.out.println("End Time" + end);

				//Start = Start.substring(0, 19);
				//end = end.substring(0, 19);
				//System.out.println("Start  " + Start);

				table1.addCell(testCase);
				table1.addCell(status);
				table1.addCell(Start);
//					        PdfPCell Start1 = new PdfPCell(new Paragraph(Start));
//					       // Start1.setHorizontalAlignment(Element.ALIGN_CENTER);
//					        Start1.setNoWrap(true);
//					     
//					      //  Start1.setVerticalAlignment(Element.ALIGN_CENTER);
//					        table1.addCell(Start1);
				table1.addCell(end);
				// table1.addCell("1");

			}

			table1.setTotalWidth(500F);

			System.out.println("width " + table1.getTotalWidth());

			PdfContentByte canvas = writer.getDirectContent();

			table1.writeSelectedRows(-10, -50, document.right() - 500, table1.getTotalHeight() + 100, canvas);

			for (int i = 0; i < totalCases.size(); i++) {

				jsonObject = (JSONObject) totalCases.get(i);

				String testCase = (String) jsonObject.get("Test Case ID");

				document.newPage();
				// document.open();

				// document.newPage();

				System.out.println("New page created");

				Paragraph header = new Paragraph(testCase + "", blueFont);

				header.setAlignment(Element.ALIGN_CENTER);

				document.add(header);

				PdfPTable table3 = new PdfPTable(4);

				table1.setTotalWidth(500F);

				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(50f); // Space before table
				table3.setSpacingAfter(20f); // Space after table

				PdfPCell header10 = new PdfPCell(new Paragraph("TEST STEPS"));
				header10.setBorderColor(BaseColor.BLACK);
				header10.setPaddingLeft(10);
				header10.setBackgroundColor(BaseColor.GRAY);
				header10.setHorizontalAlignment(Element.ALIGN_CENTER);
				header10.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell header11 = new PdfPCell(new Paragraph("STATUS"));
				header11.setBorderColor(BaseColor.BLACK);
				header11.setPaddingLeft(10);
				header11.setBackgroundColor(BaseColor.GRAY);
				header11.setHorizontalAlignment(Element.ALIGN_CENTER);
				header11.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell header12 = new PdfPCell(new Paragraph("EXPECTED VALUE"));
				header12.setBorderColor(BaseColor.BLACK);
				header12.setPaddingLeft(10);
				header12.setBackgroundColor(BaseColor.GRAY);
				header12.setHorizontalAlignment(Element.ALIGN_CENTER);
				header12.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell header13 = new PdfPCell(new Paragraph("ACTUAL VALUE"));
				header13.setBorderColor(BaseColor.BLACK);
				header13.setPaddingLeft(10);
				header13.setBackgroundColor(BaseColor.GRAY);
				header13.setHorizontalAlignment(Element.ALIGN_CENTER);
				header13.setVerticalAlignment(Element.ALIGN_MIDDLE);

				table3.addCell(header10);
				table3.addCell(header11);
				table3.addCell(header12);
				table3.addCell(header13);

				JSONArray function = (JSONArray) jsonObject.get("functions");

				for (int j = 0; j < function.size(); j++) {

					JSONObject testStep = (JSONObject) function.get(j);

					String function1 = (String) testStep.get("name");
					String status1 = (String) testStep.get("status");
					System.out.println("function" + function1);
					System.out.println("status" + status1);

					JSONArray validation = (JSONArray) testStep.get("Validation");
					String expected = "";
					String actual = "";
					if(validation.size() > 0)
					{
					JSONObject value = (JSONObject) validation.get(0);
					
					expected = (String) value.get("Expected");
					actual = (String) value.get("Actual");
					}
					table3.addCell(function1);
					table3.addCell(status1);
					table3.addCell(expected);
					table3.addCell(actual);
				}

				document.add(table3);

			}

			document.close();

			writer.close();

			// document.add(table1);

			// addTestStepReport();

		} catch (Exception e) {
			clog.info(Result.getStackMsg(e));
		}
	}

	public static JFreeChart generatePieChart1(int skip, int pass, int fail) {

		DefaultPieDataset dataSet = new DefaultPieDataset();

		dataSet.setValue("FAIL", fail);
		dataSet.setValue("SKIP", skip);
		dataSet.setValue("PASS", pass);

		JFreeChart chart = ChartFactory.createPieChart("Mobily Automation Report", dataSet, true, true, true);

		PiePlot plot = (PiePlot) chart.getPlot();

		// plot.setLegendLabelGenerator(new
		// StandardPieSectionLabelGenerator("{0}={1}({2})"));

		plot.setLabelGenerator(
				new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));

//		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));

		plot.setForegroundAlpha(1.0f);

		// plot.setBaseSectionOutlinePaint(paint);

		chart.setBackgroundPaint(Color.white);
		return chart;
	}

	public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
		PdfWriter writer = null;

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

	public static Map<String, String> readJson(String fileName) {

		JSONParser parser = new JSONParser();
		Map<String, String> map = new TreeMap<String, String>();
		try {
			Object obj = null;
			try {
				// obj = parser.parse(new
				// FileReader(System.getProperty("user.dir")+"/src/test/resources/testdata/cao/"+
				// fileName +".json"));
				obj = parser.parse(new FileReader(("C:/Users/jayakumarj/Desktop/Report.json")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("Test Case ID");

			// @SuppressWarnings("unchecked")
			Iterator<String> keysItr = jsonObject.keySet().iterator();
			while (keysItr.hasNext()) {
				String key = (String) keysItr.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);

			}

			System.out.println("Map Value is " + map);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void addTestStepReport() {

		try {
			String report = bpath.get() + "/report/" + runid.get() + "/Report.json";
			
			
			//String report = "C:/Users/jayakumarj/Desktop/New.json";
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;

			JSONArray totalCases;

			totalCases = (JSONArray) parser.parse(new FileReader(report));

			// int totalNumberOfCases=totalCases.size();

			for (int i = 0; i < totalCases.size(); i++) {

				jsonObject = (JSONObject) totalCases.get(i);

				String testCase = (String) jsonObject.get("Test Case ID");
				String fileName = bpath.get() + "/report/" + runid.get() + "/"+testCase+".pdf";

				Document document1 = new Document();
				PdfWriter writer1 = PdfWriter.getInstance(document1,
						new FileOutputStream(fileName));
				// document1.newPage();
				document1.open();

				// document.newPage();

				System.out.println("New page created");

				PdfPTable table3 = new PdfPTable(1);

				table3.setWidthPercentage(50); // Width 100%
				table3.setSpacingBefore(50f); // Space before table
				table3.setSpacingAfter(20f); // Space after table

				PdfPCell header10 = new PdfPCell(new Paragraph("TEST CASE"));
				header10.setBorderColor(BaseColor.BLACK);
				header10.setPaddingLeft(10);
				header10.setBackgroundColor(BaseColor.GRAY);
				header10.setHorizontalAlignment(Element.ALIGN_CENTER);
				header10.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell header11 = new PdfPCell(new Paragraph("STATUS"));
				header11.setBorderColor(BaseColor.BLACK);
				header11.setPaddingLeft(10);
				header11.setBackgroundColor(BaseColor.GRAY);
				header11.setHorizontalAlignment(Element.ALIGN_CENTER);
				header11.setVerticalAlignment(Element.ALIGN_MIDDLE);

				table3.addCell(header10);
				table3.addCell(header11);
				document1.add(table3);
				document1.close();
				writer1.close();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
