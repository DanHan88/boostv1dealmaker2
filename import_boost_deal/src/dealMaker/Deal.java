package dealMaker;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Deal {

	
		@JsonProperty("PieceCid") //baga6ea4seaqgkhzmtbm6et5jnvqqur3qenpiaigneehmbbdc6uvye6najbx6cdy
		private String pieceCid;
	 
	 	@JsonProperty("PublishCid") //bafy2bzacedn7wlexa2jj5nnh3d4ob5qegwtw776ek5o3qltseua7xbfx7wbdk
	    private String publishCid;

	    @JsonProperty("CreatedAt")
	    private String createdAt;

	    @JsonProperty("IsVerified")
	    private boolean isVerified;
	    
	    @JsonProperty("PieceSize")
	    private String pieceSize;

	    @JsonProperty("KeepUnsealedCopy")
	    private boolean keepUnsealedCopy;
	    
	    int index;
	    
	    String carSize;
	    
	    
	    

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getCarSize() {
			return carSize;
		}

		public void setCarSize(String carSize) {
			this.carSize = carSize;
		}

		public String getPieceCid() {
			return pieceCid;
		}

		public void setPieceCid(String pieceCid) {
			this.pieceCid = pieceCid;
		}

		public String getPublishCid() {
			return publishCid;
		}

		public void setPublishCid(String publishCid) {
			this.publishCid = publishCid;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public boolean isVerified() {
			return isVerified;
		}

		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}

		

		public String getPieceSize() {
			return pieceSize;
		}

		public void setPieceSize(String pieceSize) {
			this.pieceSize = pieceSize;
		}


		public boolean isKeepUnsealedCopy() {
			return keepUnsealedCopy;
		}

		public void setKeepUnsealedCopy(boolean keepUnsealedCopy) {
			this.keepUnsealedCopy = keepUnsealedCopy;
		}

		
	    

}
