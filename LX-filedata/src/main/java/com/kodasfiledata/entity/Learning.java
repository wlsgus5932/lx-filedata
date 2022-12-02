package com.kodasfiledata.entity;

public class Learning {
	private String rownumber;
	private int total_count;
	
	private String scnr_id;
	private String scnr_atrb_id;
	private String oprt_ymd;
	private String oprt_tm;
	private String oprt_sped_val;
	private String oprt_area_nm;
	private String oprt_dstn_cnt;
	private String ilmn_clcd;
	private String wthr_dscd;
	private String vhcl_mvob_kncd;
	private String road_kncd;
	private String road_crcm_clcd;
	private String test_envr_dscd;
	private String ilmn_cl_nm;
	private String wthr_ds_nm;
	private String vhcl_mvob_kn_nm;
	private String road_kn_nm;
	private String road_crcm_cl_nm;
	private String test_envr_ds_nm;
	private String path_file_nm;
	private String detail_id;
	private String hedr_id;
	private String imag_id;
	private String data_type;
	private String humn_grup_cnt;
	private String vhcl_grup_cnt;
	private String humn_cnt;
	private String pasg_cnt;
	private String trck_cnt;
	private String bus_cnt;
	private String mtcy_cnt;
	private String omnb_cnt;
	private String spac_cnt;
	private String brm_sple_cnt;
	private String brm_cswk_cnt;
	private String brm_sdbp_cnt;
	private String brs_pncg_cnt;
	private String brs_rk_cnt;
	private String btl_ctly_cnt;
	private String tl_butl_cnt;
	private String btl_bitl_cnt;
	private String btl_retl_cnt;
	private String bet_cern_cnt;
	private String srm_stle_cnt;
	private String srm_cswk_cnt;
	private String srs_pncg_cnt;
	private String srs_rk_cnt;
	private String stl_ctly_cnt;
	private String stl_butl_cnt;
	private String stl_bitl_cnt;
	private String stl_retl_cnt;
	private String set_stlt_cnt;
	private String set_trs_cnt;
	private String set_bud_cnt;
	private String set_cern_cnt;
	private String sple_cnt;
	private String cswk_cnt;
	private String sdbp_cnt;
	private String rk_cnt;
	private String tligt_cnt;
	private String path_nm;
	private String file_nm;
	private String sen_tp;
	private String imag_file_path;
	private String imag_file_nm;
	private String insn_mask_imag_file_path;
	private String insn_mask_imag_file_nm;
	private String labl_mask_imag_file_path;
	private String labl_mask_imag_file_nm;
	private String json_file_path;
	private String json_file_nm;
	private String pstn_no;
	
	
	public String getImag_file_path() {
		return imag_file_path;
	}
	public void setImag_file_path(String imag_file_path) {
		this.imag_file_path = imag_file_path;
	}
	public String getImag_file_nm() {
		return imag_file_nm;
	}
	public void setImag_file_nm(String imag_file_nm) {
		this.imag_file_nm = imag_file_nm;
	}
	public String getPstn_no() {
		return pstn_no;
	}
	public void setPstn_no(String pstn_no) {
		this.pstn_no = pstn_no;
	}
	public String getImage_file_path() {
		return imag_file_path;
	}
	public void setImage_file_path(String image_file_path) {
		this.imag_file_path = image_file_path;
	}
	public String getImage_file_nm() {
		return imag_file_nm;
	}
	public void setImage_file_nm(String image_file_nm) {
		this.imag_file_nm = image_file_nm;
	}
	public String getInsn_mask_imag_file_path() {
		return insn_mask_imag_file_path;
	}
	public void setInsn_mask_imag_file_path(String insn_mask_imag_file_path) {
		this.insn_mask_imag_file_path = insn_mask_imag_file_path;
	}
	public String getInsn_mask_imag_file_nm() {
		return insn_mask_imag_file_nm;
	}
	public void setInsn_mask_imag_file_nm(String insn_mask_imag_file_nm) {
		this.insn_mask_imag_file_nm = insn_mask_imag_file_nm;
	}
	public String getLabl_mask_imag_file_path() {
		return labl_mask_imag_file_path;
	}
	public void setLabl_mask_imag_file_path(String labl_mask_imag_file_path) {
		this.labl_mask_imag_file_path = labl_mask_imag_file_path;
	}
	public String getLabl_mask_imag_file_nm() {
		return labl_mask_imag_file_nm;
	}
	public void setLabl_mask_imag_file_nm(String labl_mask_imag_file_nm) {
		this.labl_mask_imag_file_nm = labl_mask_imag_file_nm;
	}
	public String getJson_file_path() {
		return json_file_path;
	}
	public void setJson_file_path(String json_file_path) {
		this.json_file_path = json_file_path;
	}
	public String getJson_file_nm() {
		return json_file_nm;
	}
	public void setJson_file_nm(String json_file_nm) {
		this.json_file_nm = json_file_nm;
	}
	@Override
	public String toString() {
		return "Learning [rownumber=" + rownumber + ", total_count=" + total_count + ", scnr_id=" + scnr_id
				+ ", scnr_atrb_id=" + scnr_atrb_id + ", oprt_ymd=" + oprt_ymd + ", oprt_tm=" + oprt_tm
				+ ", oprt_sped_val=" + oprt_sped_val + ", oprt_area_nm=" + oprt_area_nm + ", oprt_dstn_cnt="
				+ oprt_dstn_cnt + ", ilmn_clcd=" + ilmn_clcd + ", wthr_dscd=" + wthr_dscd + ", vhcl_mvob_kncd="
				+ vhcl_mvob_kncd + ", road_kncd=" + road_kncd + ", road_crcm_clcd=" + road_crcm_clcd
				+ ", test_envr_dscd=" + test_envr_dscd + ", ilmn_cl_nm=" + ilmn_cl_nm + ", wthr_ds_nm=" + wthr_ds_nm
				+ ", vhcl_mvob_kn_nm=" + vhcl_mvob_kn_nm + ", road_kn_nm=" + road_kn_nm + ", road_crcm_cl_nm="
				+ road_crcm_cl_nm + ", test_envr_ds_nm=" + test_envr_ds_nm + ", path_file_nm=" + path_file_nm
				+ ", detail_id=" + detail_id + ", hedr_id=" + hedr_id + ", imag_id=" + imag_id + ", data_type="
				+ data_type + ", humn_grup_cnt=" + humn_grup_cnt + ", vhcl_grup_cnt=" + vhcl_grup_cnt + ", humn_cnt="
				+ humn_cnt + ", pasg_cnt=" + pasg_cnt + ", trck_cnt=" + trck_cnt + ", bus_cnt=" + bus_cnt
				+ ", mtcy_cnt=" + mtcy_cnt + ", omnb_cnt=" + omnb_cnt + ", spac_cnt=" + spac_cnt + ", brm_sple_cnt="
				+ brm_sple_cnt + ", brm_cswk_cnt=" + brm_cswk_cnt + ", brm_sdbp_cnt=" + brm_sdbp_cnt + ", brs_pncg_cnt="
				+ brs_pncg_cnt + ", brs_rk_cnt=" + brs_rk_cnt + ", btl_ctly_cnt=" + btl_ctly_cnt + ", tl_butl_cnt="
				+ tl_butl_cnt + ", btl_bitl_cnt=" + btl_bitl_cnt + ", btl_retl_cnt=" + btl_retl_cnt + ", bet_cern_cnt="
				+ bet_cern_cnt + ", srm_stle_cnt=" + srm_stle_cnt + ", srm_cswk_cnt=" + srm_cswk_cnt + ", srs_pncg_cnt="
				+ srs_pncg_cnt + ", srs_rk_cnt=" + srs_rk_cnt + ", stl_ctly_cnt=" + stl_ctly_cnt + ", stl_butl_cnt="
				+ stl_butl_cnt + ", stl_bitl_cnt=" + stl_bitl_cnt + ", stl_retl_cnt=" + stl_retl_cnt + ", set_stlt_cnt="
				+ set_stlt_cnt + ", set_trs_cnt=" + set_trs_cnt + ", set_bud_cnt=" + set_bud_cnt + ", set_cern_cnt="
				+ set_cern_cnt + ", sple_cnt=" + sple_cnt + ", cswk_cnt=" + cswk_cnt + ", sdbp_cnt=" + sdbp_cnt
				+ ", rk_cnt=" + rk_cnt + ", tligt_cnt=" + tligt_cnt + ", path_nm=" + path_nm + ", file_nm=" + file_nm
				+ ", sen_tp=" + sen_tp + ", image_file_path=" + imag_file_path + ", image_file_nm=" + imag_file_nm
				+ ", insn_mask_imag_file_path=" + insn_mask_imag_file_path + ", insn_mask_imag_file_nm="
				+ insn_mask_imag_file_nm + ", labl_mask_imag_file_path=" + labl_mask_imag_file_path
				+ ", labl_mask_imag_file_nm=" + labl_mask_imag_file_nm + ", json_file_path=" + json_file_path
				+ ", json_file_nm=" + json_file_nm + "]";
	}
	public String getRownumber() {
		return rownumber;
	}
	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public String getScnr_id() {
		return scnr_id;
	}
	public void setScnr_id(String scnr_id) {
		this.scnr_id = scnr_id;
	}
	public String getScnr_atrb_id() {
		return scnr_atrb_id;
	}
	public void setScnr_atrb_id(String scnr_atrb_id) {
		this.scnr_atrb_id = scnr_atrb_id;
	}
	public String getOprt_ymd() {
		return oprt_ymd;
	}
	public void setOprt_ymd(String oprt_ymd) {
		this.oprt_ymd = oprt_ymd;
	}
	public String getOprt_tm() {
		return oprt_tm;
	}
	public void setOprt_tm(String oprt_tm) {
		this.oprt_tm = oprt_tm;
	}
	public String getOprt_sped_val() {
		return oprt_sped_val;
	}
	public void setOprt_sped_val(String oprt_sped_val) {
		this.oprt_sped_val = oprt_sped_val;
	}
	public String getOprt_area_nm() {
		return oprt_area_nm;
	}
	public void setOprt_area_nm(String oprt_area_nm) {
		this.oprt_area_nm = oprt_area_nm;
	}
	public String getOprt_dstn_cnt() {
		return oprt_dstn_cnt;
	}
	public void setOprt_dstn_cnt(String oprt_dstn_cnt) {
		this.oprt_dstn_cnt = oprt_dstn_cnt;
	}
	public String getIlmn_clcd() {
		return ilmn_clcd;
	}
	public void setIlmn_clcd(String ilmn_clcd) {
		this.ilmn_clcd = ilmn_clcd;
	}
	public String getWthr_dscd() {
		return wthr_dscd;
	}
	public void setWthr_dscd(String wthr_dscd) {
		this.wthr_dscd = wthr_dscd;
	}
	public String getVhcl_mvob_kncd() {
		return vhcl_mvob_kncd;
	}
	public void setVhcl_mvob_kncd(String vhcl_mvob_kncd) {
		this.vhcl_mvob_kncd = vhcl_mvob_kncd;
	}
	public String getRoad_kncd() {
		return road_kncd;
	}
	public void setRoad_kncd(String road_kncd) {
		this.road_kncd = road_kncd;
	}
	public String getRoad_crcm_clcd() {
		return road_crcm_clcd;
	}
	public void setRoad_crcm_clcd(String road_crcm_clcd) {
		this.road_crcm_clcd = road_crcm_clcd;
	}
	public String getTest_envr_dscd() {
		return test_envr_dscd;
	}
	public void setTest_envr_dscd(String test_envr_dscd) {
		this.test_envr_dscd = test_envr_dscd;
	}
	public String getIlmn_cl_nm() {
		return ilmn_cl_nm;
	}
	public void setIlmn_cl_nm(String ilmn_cl_nm) {
		this.ilmn_cl_nm = ilmn_cl_nm;
	}
	public String getWthr_ds_nm() {
		return wthr_ds_nm;
	}
	public void setWthr_ds_nm(String wthr_ds_nm) {
		this.wthr_ds_nm = wthr_ds_nm;
	}
	public String getVhcl_mvob_kn_nm() {
		return vhcl_mvob_kn_nm;
	}
	public void setVhcl_mvob_kn_nm(String vhcl_mvob_kn_nm) {
		this.vhcl_mvob_kn_nm = vhcl_mvob_kn_nm;
	}
	public String getRoad_kn_nm() {
		return road_kn_nm;
	}
	public void setRoad_kn_nm(String road_kn_nm) {
		this.road_kn_nm = road_kn_nm;
	}
	public String getRoad_crcm_cl_nm() {
		return road_crcm_cl_nm;
	}
	public void setRoad_crcm_cl_nm(String road_crcm_cl_nm) {
		this.road_crcm_cl_nm = road_crcm_cl_nm;
	}
	public String getTest_envr_ds_nm() {
		return test_envr_ds_nm;
	}
	public void setTest_envr_ds_nm(String test_envr_ds_nm) {
		this.test_envr_ds_nm = test_envr_ds_nm;
	}
	public String getPath_file_nm() {
		return path_file_nm;
	}
	public void setPath_file_nm(String path_file_nm) {
		this.path_file_nm = path_file_nm;
	}
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public String getHedr_id() {
		return hedr_id;
	}
	public void setHedr_id(String hedr_id) {
		this.hedr_id = hedr_id;
	}
	public String getImag_id() {
		return imag_id;
	}
	public void setImag_id(String imag_id) {
		this.imag_id = imag_id;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getHumn_grup_cnt() {
		return humn_grup_cnt;
	}
	public void setHumn_grup_cnt(String humn_grup_cnt) {
		this.humn_grup_cnt = humn_grup_cnt;
	}
	public String getVhcl_grup_cnt() {
		return vhcl_grup_cnt;
	}
	public void setVhcl_grup_cnt(String vhcl_grup_cnt) {
		this.vhcl_grup_cnt = vhcl_grup_cnt;
	}
	public String getHumn_cnt() {
		return humn_cnt;
	}
	public void setHumn_cnt(String humn_cnt) {
		this.humn_cnt = humn_cnt;
	}
	public String getPasg_cnt() {
		return pasg_cnt;
	}
	public void setPasg_cnt(String pasg_cnt) {
		this.pasg_cnt = pasg_cnt;
	}
	public String getTrck_cnt() {
		return trck_cnt;
	}
	public void setTrck_cnt(String trck_cnt) {
		this.trck_cnt = trck_cnt;
	}
	public String getBus_cnt() {
		return bus_cnt;
	}
	public void setBus_cnt(String bus_cnt) {
		this.bus_cnt = bus_cnt;
	}
	public String getMtcy_cnt() {
		return mtcy_cnt;
	}
	public void setMtcy_cnt(String mtcy_cnt) {
		this.mtcy_cnt = mtcy_cnt;
	}
	public String getOmnb_cnt() {
		return omnb_cnt;
	}
	public void setOmnb_cnt(String omnb_cnt) {
		this.omnb_cnt = omnb_cnt;
	}
	public String getSpac_cnt() {
		return spac_cnt;
	}
	public void setSpac_cnt(String spac_cnt) {
		this.spac_cnt = spac_cnt;
	}
	public String getBrm_sple_cnt() {
		return brm_sple_cnt;
	}
	public void setBrm_sple_cnt(String brm_sple_cnt) {
		this.brm_sple_cnt = brm_sple_cnt;
	}
	public String getBrm_cswk_cnt() {
		return brm_cswk_cnt;
	}
	public void setBrm_cswk_cnt(String brm_cswk_cnt) {
		this.brm_cswk_cnt = brm_cswk_cnt;
	}
	public String getBrm_sdbp_cnt() {
		return brm_sdbp_cnt;
	}
	public void setBrm_sdbp_cnt(String brm_sdbp_cnt) {
		this.brm_sdbp_cnt = brm_sdbp_cnt;
	}
	public String getBrs_pncg_cnt() {
		return brs_pncg_cnt;
	}
	public void setBrs_pncg_cnt(String brs_pncg_cnt) {
		this.brs_pncg_cnt = brs_pncg_cnt;
	}
	public String getBrs_rk_cnt() {
		return brs_rk_cnt;
	}
	public void setBrs_rk_cnt(String brs_rk_cnt) {
		this.brs_rk_cnt = brs_rk_cnt;
	}
	public String getBtl_ctly_cnt() {
		return btl_ctly_cnt;
	}
	public void setBtl_ctly_cnt(String btl_ctly_cnt) {
		this.btl_ctly_cnt = btl_ctly_cnt;
	}
	public String getTl_butl_cnt() {
		return tl_butl_cnt;
	}
	public void setTl_butl_cnt(String tl_butl_cnt) {
		this.tl_butl_cnt = tl_butl_cnt;
	}
	public String getBtl_bitl_cnt() {
		return btl_bitl_cnt;
	}
	public void setBtl_bitl_cnt(String btl_bitl_cnt) {
		this.btl_bitl_cnt = btl_bitl_cnt;
	}
	public String getBtl_retl_cnt() {
		return btl_retl_cnt;
	}
	public void setBtl_retl_cnt(String btl_retl_cnt) {
		this.btl_retl_cnt = btl_retl_cnt;
	}
	public String getBet_cern_cnt() {
		return bet_cern_cnt;
	}
	public void setBet_cern_cnt(String bet_cern_cnt) {
		this.bet_cern_cnt = bet_cern_cnt;
	}
	public String getSrm_stle_cnt() {
		return srm_stle_cnt;
	}
	public void setSrm_stle_cnt(String srm_stle_cnt) {
		this.srm_stle_cnt = srm_stle_cnt;
	}
	public String getSrm_cswk_cnt() {
		return srm_cswk_cnt;
	}
	public void setSrm_cswk_cnt(String srm_cswk_cnt) {
		this.srm_cswk_cnt = srm_cswk_cnt;
	}
	public String getSrs_pncg_cnt() {
		return srs_pncg_cnt;
	}
	public void setSrs_pncg_cnt(String srs_pncg_cnt) {
		this.srs_pncg_cnt = srs_pncg_cnt;
	}
	public String getSrs_rk_cnt() {
		return srs_rk_cnt;
	}
	public void setSrs_rk_cnt(String srs_rk_cnt) {
		this.srs_rk_cnt = srs_rk_cnt;
	}
	public String getStl_ctly_cnt() {
		return stl_ctly_cnt;
	}
	public void setStl_ctly_cnt(String stl_ctly_cnt) {
		this.stl_ctly_cnt = stl_ctly_cnt;
	}
	public String getStl_butl_cnt() {
		return stl_butl_cnt;
	}
	public void setStl_butl_cnt(String stl_butl_cnt) {
		this.stl_butl_cnt = stl_butl_cnt;
	}
	public String getStl_bitl_cnt() {
		return stl_bitl_cnt;
	}
	public void setStl_bitl_cnt(String stl_bitl_cnt) {
		this.stl_bitl_cnt = stl_bitl_cnt;
	}
	public String getStl_retl_cnt() {
		return stl_retl_cnt;
	}
	public void setStl_retl_cnt(String stl_retl_cnt) {
		this.stl_retl_cnt = stl_retl_cnt;
	}
	public String getSet_stlt_cnt() {
		return set_stlt_cnt;
	}
	public void setSet_stlt_cnt(String set_stlt_cnt) {
		this.set_stlt_cnt = set_stlt_cnt;
	}
	public String getSet_trs_cnt() {
		return set_trs_cnt;
	}
	public void setSet_trs_cnt(String set_trs_cnt) {
		this.set_trs_cnt = set_trs_cnt;
	}
	public String getSet_bud_cnt() {
		return set_bud_cnt;
	}
	public void setSet_bud_cnt(String set_bud_cnt) {
		this.set_bud_cnt = set_bud_cnt;
	}
	public String getSet_cern_cnt() {
		return set_cern_cnt;
	}
	public void setSet_cern_cnt(String set_cern_cnt) {
		this.set_cern_cnt = set_cern_cnt;
	}
	public String getSple_cnt() {
		return sple_cnt;
	}
	public void setSple_cnt(String sple_cnt) {
		this.sple_cnt = sple_cnt;
	}
	public String getCswk_cnt() {
		return cswk_cnt;
	}
	public void setCswk_cnt(String cswk_cnt) {
		this.cswk_cnt = cswk_cnt;
	}
	public String getSdbp_cnt() {
		return sdbp_cnt;
	}
	public void setSdbp_cnt(String sdbp_cnt) {
		this.sdbp_cnt = sdbp_cnt;
	}
	public String getRk_cnt() {
		return rk_cnt;
	}
	public void setRk_cnt(String rk_cnt) {
		this.rk_cnt = rk_cnt;
	}
	public String getTligt_cnt() {
		return tligt_cnt;
	}
	public void setTligt_cnt(String tligt_cnt) {
		this.tligt_cnt = tligt_cnt;
	}
	public String getPath_nm() {
		return path_nm;
	}
	public void setPath_nm(String path_nm) {
		this.path_nm = path_nm;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	public String getSen_tp() {
		return sen_tp;
	}
	public void setSen_tp(String sen_tp) {
		this.sen_tp = sen_tp;
	}

}
