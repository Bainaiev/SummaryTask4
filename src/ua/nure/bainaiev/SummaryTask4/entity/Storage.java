package ua.nure.bainaiev.SummaryTask4.entity;


public class Storage extends Entity {
    private int testId;
    private String result;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Storage(){}

    public Storage(int id, int testId, String result) {
        this.id = id;
        this.testId = testId;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "testId=" + testId +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Storage storage = (Storage) o;

        if (testId != storage.testId) return false;
        return result.equals(storage.result);

    }

    @Override
    public int hashCode() {
        int result1 = super.hashCode();
        result1 = 31 * result1 + testId;
        result1 = 31 * result1 + result.hashCode();
        return result1;
    }
}
