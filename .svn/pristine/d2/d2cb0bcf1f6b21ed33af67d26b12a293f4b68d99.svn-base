package com.oio.wawj.bean;

/**
 * ChannelId entity. @author MyEclipse Persistence Tools
 */

public class ChannelId implements java.io.Serializable {

	// Fields

	private Integer channelId;
	private String channelName;

	// Constructors

	/** default constructor */
	public ChannelId() {
	}

	/** full constructor */
	public ChannelId(Integer channelId, String channelName) {
		this.channelId = channelId;
		this.channelName = channelName;
	}

	// Property accessors

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChannelId))
			return false;
		ChannelId castOther = (ChannelId) other;

		return ((this.getChannelId() == castOther.getChannelId()) || (this
				.getChannelId() != null && castOther.getChannelId() != null && this
				.getChannelId().equals(castOther.getChannelId())))
				&& ((this.getChannelName() == castOther.getChannelName()) || (this
						.getChannelName() != null
						&& castOther.getChannelName() != null && this
						.getChannelName().equals(castOther.getChannelName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getChannelId() == null ? 0 : this.getChannelId().hashCode());
		result = 37
				* result
				+ (getChannelName() == null ? 0 : this.getChannelName()
						.hashCode());
		return result;
	}

}