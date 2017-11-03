
package com.oio.wawj.dao;



import java.util.Map;

import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.util.PageListData;



public interface AxbDAO extends BaseDAO<SubsRela,Long>{
	public PageListData findList(Map<String, Object> param, int currentPage,
			int pageSize);

	public SubsRela findByXnumAndAnum(final String xnum,final String anum);
	
}
