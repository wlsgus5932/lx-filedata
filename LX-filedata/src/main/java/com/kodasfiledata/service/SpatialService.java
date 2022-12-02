package com.kodasfiledata.service;

import java.util.List;


import com.kodasfiledata.entity.Spatial;
import com.kodasfiledata.parameter.SpatialParameter;

public interface SpatialService {
	public List<Spatial> getSpatialList(SpatialParameter param);

}
