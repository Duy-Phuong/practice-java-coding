package enums;

import java.util.ArrayList;

/**
 * Example class
 */
public enum TestModel {
    TYPE_A("TYPE_A", "type_a"),
    TYPE_B("TYPE_B", "type_b"),
    TYPE_C("TYPE_C", "type_c");
    private String id;
    private String label;

    TestModel(String id, String label) {
        this.id = id;
        this.label = label;
    }


    public String getId() {
        return id;
    }

    /**
     * Get values form enum without some value
     *
     * @return
     */
    public static TestModel[] getListTestModel() {
        ArrayList<TestModel> list = new ArrayList<>();
        for (TestModel value : TestModel.values()) {
            if (!value.id.equals(TYPE_C.id)) {
                list.add(value);
            }
        }
        TestModel[] TestModel2 = new TestModel[list.size()];
        for (int i = 0; i < list.size(); i++) {
            TestModel2[i] = (TestModel) list.get(i);
        }
        return TestModel2;
    }

    public static void main(String[] args) {
        TestModel[] testModels = TestModel.getListTestModel();
        for (TestModel TestModel: testModels) {
            System.out.println(TestModel.getId());
        }
    }
}