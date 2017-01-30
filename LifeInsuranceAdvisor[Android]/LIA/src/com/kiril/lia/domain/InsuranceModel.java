package com.kiril.lia.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class InsuranceModel implements Parcelable {

	private String gender;
	private int years;
	private int premium;
	private int period;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(years);
		dest.writeString(gender);
		dest.writeInt(premium);
		dest.writeInt(period);

	}

	// constructor that takes a Parcel and returns
	// an object populated with it's values
	private InsuranceModel(Parcel in) {
		years = in.readInt();
		gender = in.readString();
		premium = in.readInt();
		period = in.readInt();

	}
	public InsuranceModel() {
	

	}

	// this is used to regenerate your object. All Parcelables must have a
	// CREATOR that implements these two methods
	public static final Parcelable.Creator<InsuranceModel> CREATOR = new Creator<InsuranceModel>() {

		@Override
		public InsuranceModel[] newArray(int size) {
			return new InsuranceModel[size];
		}

		@Override
		public InsuranceModel createFromParcel(Parcel source) {
			return new InsuranceModel(source);
		}
	};

}
