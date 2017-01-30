package com.kiril.lia.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kiril.lia.domain.CompanyResultModel;

public class JsonParser {
	public static List<CompanyResultModel> parseJson(String content) {

		try {
			JSONArray ar = new JSONArray(content);
			List<CompanyResultModel> results = new ArrayList<CompanyResultModel>();

			for (int i = 0; i < ar.length(); i++) {

				JSONObject obj = ar.getJSONObject(i);
				CompanyResultModel model = new CompanyResultModel();

				model.setOsigurenaID(obj.getInt("OsigurenaID"));
				model.setOsigurenaSuma(obj.getInt("OsigurenaSuma"));

				results.add(model);
			}

			return results;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}

}
