package com.avee.utility.chart;

public class ChartData {
	
	private Datasets[] datasets;
	private String[] labels;

	public Datasets[] getDatasets() {
		return datasets;
	}
	public void setDatasets(Datasets[] datasets) {
		this.datasets = datasets;
	}

	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	
	
	
	
	public class Datasets {
		private String[] backgroundColor;
		private String[] data;
		
		public String[] getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String[] backgroundColor) {
			this.backgroundColor = backgroundColor;
		}

		public String[] getData() {
			return data;
		}
		public void setData(String[] data) {
			this.data = data;
		}
	}
}