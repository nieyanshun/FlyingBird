package org.flying.bird.protocol.packet;

import org.flying.bird.protocol.Header;

/**
 * @author nieyanshun
 *
 */
public class MessageHeader implements Header {

	private short magic;

	private int serialId;

	private int bodyLength;

	private int timeStamp;

	private byte version;

	private byte ext;

	@Override
	public int serialId() {
		return serialId;
	}

	@Override
	public int bodyLength() {
		return bodyLength;
	}

	@Override
	public int timeStamp() {
		return timeStamp;
	}

	@Override
	public byte version() {
		return version;
	}

	@Override
	public byte ext() {
		return ext;
	}

	@Override
	public boolean isReq() {
		return (magic == (MAGIC & REQ_MASK));
	}

	@Override
	public boolean isRet() {
		return (magic == (MAGIC & RET_MASK));
	}

	public static class Builder {
		private short magic;
		private int serialId;
		private int bodyLength;
		private int timeStamp;
		private byte version;
		private byte ext;

		public Builder serialId(int serialId) {
			this.serialId = serialId;
			return this;
		}

		public Builder bodyLength(int bodyLength) {
			this.bodyLength = bodyLength;
			return this;
		}

		public Builder timeStamp(int timeStamp) {
			this.timeStamp = timeStamp;
			return this;
		}

		public Builder version(byte version) {
			this.version = version;
			return this;
		}

		public Builder ext(byte ext) {
			this.ext = ext;
			return this;
		}

		public MessageHeader buildReq() {
			this.magic = (MAGIC & REQ_MASK);
			return new MessageHeader(this);
		}

		public MessageHeader buildRet() {
			this.magic = (MAGIC & REQ_MASK);
			return new MessageHeader(this);
		}

	}

	private MessageHeader(Builder builder) {
		this.magic = builder.magic;
		this.serialId = builder.serialId;
		this.bodyLength = builder.bodyLength;
		this.timeStamp = builder.timeStamp;
		this.version = (0X00 == builder.version) ? VERSION : builder.version;
		this.ext = builder.ext;
	}

	@Override
	public String toString() {
		return "MessageHeader [magic=" + magic + ", serialId=" + serialId + ", bodyLength=" + bodyLength
				+ ", timeStamp=" + timeStamp + ", version=" + version + ", ext=" + ext + "]";
	}
	
}
