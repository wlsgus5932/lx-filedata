package com.kodasfiledata.entity;

public class Ldm {
	
	private String ldm3ds_models;
	private String bounding_box_file_nm;
	private String segmentation_file_nm;
	private String static_file_nm;
	private String area_nm;
	private String ltud1_crdn_val;
	private String lngd1_crdn_val;
	private String ltud2_crdn_val;
	private String lngd2_crdn_val;
	
	public String getLdm3ds_models() {
		return ldm3ds_models;
	}
	public void setLdm3ds_models(String ldm3ds_models) {
		this.ldm3ds_models = ldm3ds_models;
	}
	public String getBounding_box_file_nm() {
		return bounding_box_file_nm;
	}
	public void setBounding_box_file_nm(String bounding_box_file_nm) {
		this.bounding_box_file_nm = bounding_box_file_nm;
	}
	public String getSegmentation_file_nm() {
		return segmentation_file_nm;
	}
	public void setSegmentation_file_nm(String segmentation_file_nm) {
		this.segmentation_file_nm = segmentation_file_nm;
	}
	public String getStatic_file_nm() {
		return static_file_nm;
	}
	public void setStatic_file_nm(String static_file_nm) {
		this.static_file_nm = static_file_nm;
	}
	public String getArea_nm() {
		return area_nm;
	}
	public void setArea_nm(String area_nm) {
		this.area_nm = area_nm;
	}
	public String getLtud1_crdn_val() {
		return ltud1_crdn_val;
	}
	public void setLtud1_crdn_val(String ltud1_crdn_val) {
		this.ltud1_crdn_val = ltud1_crdn_val;
	}
	public String getLngd1_crdn_val() {
		return lngd1_crdn_val;
	}
	public void setLngd1_crdn_val(String lngd1_crdn_val) {
		this.lngd1_crdn_val = lngd1_crdn_val;
	}
	public String getLtud2_crdn_val() {
		return ltud2_crdn_val;
	}
	public void setLtud2_crdn_val(String ltud2_crdn_val) {
		this.ltud2_crdn_val = ltud2_crdn_val;
	}
	public String getLngd2_crdn_val() {
		return lngd2_crdn_val;
	}
	public void setLngd2_crdn_val(String lngd2_crdn_val) {
		this.lngd2_crdn_val = lngd2_crdn_val;
	}
	@Override
	public String toString() {
		return "Ldm [ldm3ds_models=" + ldm3ds_models + ", bounding_box_file_nm=" + bounding_box_file_nm
				+ ", segmentation_file_nm=" + segmentation_file_nm + ", static_file_nm=" + static_file_nm + ", area_nm="
				+ area_nm + ", ltud1_crdn_val=" + ltud1_crdn_val + ", lngd1_crdn_val=" + lngd1_crdn_val
				+ ", ltud2_crdn_val=" + ltud2_crdn_val + ", lngd2_crdn_val=" + lngd2_crdn_val + "]";
	}
	
	
		

}
