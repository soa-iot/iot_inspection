package cn.zg.entity.daoEntity;

import java.util.Arrays;

public class Test {
	private String tableName;
	private String[] inspectionTimes;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String[] getInspectionTimes() {
		return inspectionTimes;
	}
	public void setInspectionTimes(String[] inspectionTimes) {
		this.inspectionTimes = inspectionTimes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(inspectionTimes);
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (!Arrays.equals(inspectionTimes, other.inspectionTimes))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Test [tableName=" + tableName + ", inspectionTimes=" + Arrays.toString(inspectionTimes) + "]";
	}
	
	
}
