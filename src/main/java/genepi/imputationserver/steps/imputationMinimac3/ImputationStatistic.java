package genepi.imputationserver.steps.imputationMinimac3;

public class ImputationStatistic {

	private long phasingTime = -1;

	private long imputationTime = -1;

	private long importTime = -1;

	public long getPhasingTime() {
		return phasingTime;
	}

	public void setPhasingTime(long phasingTime) {
		this.phasingTime = phasingTime;
	}

	public long getImputationTime() {
		return imputationTime;
	}

	public void setImputationTime(long imputationTime) {
		this.imputationTime = imputationTime;
	}

	public void setImportTime(long importTime) {
		this.importTime = importTime;
	}

	public long getImportTime() {
		return importTime;
	}

}
