public class Worker {
    String name;
    int age;
    int category;
    int seniority;

    public static final int CAT_EMPLOYEE = 0;
    public static final int CAT_MANAGER = 1;
    public static final int CAT_EXECUTIVE = 2;
    public static final int SEN_JUNIOR = 0;
    public static final int SEN_SEMI_SENIOR = 1;
    public static final int SEN_SENIOR = 2;

    public Worker(String name, int age, int category, int seniority) {
        if (category < CAT_EMPLOYEE || category > CAT_EXECUTIVE) {
            throw new CategoryException("ERROR: INCORRECT CATEGORY");
        }
        if (seniority < SEN_JUNIOR || seniority > SEN_SENIOR) {
            throw new SeniorityException("ERROR: INCORRECT CAREER");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public double calculateSalary() {
        double salary = 0;
        switch (category) {
            case 0:
                if (seniority == SEN_JUNIOR) {
                    salary = 607 * 1.15 + 150;
                }
                else if (seniority == SEN_SEMI_SENIOR) {
                    salary = 607 * 1.15 + 300;
                }
                else if (seniority == SEN_SENIOR) {
                    salary = 607 * 1.15 + 600;
                }
                break;
            case 1:
                if (seniority == SEN_JUNIOR) {
                    salary = 607 * 1.35 + 150;
                }
                else if (seniority == SEN_SEMI_SENIOR) {
                    salary = 607 * 1.35 + 300;
                }
                else if (seniority == SEN_SENIOR) {
                    salary = 607 * 1.35 + 600;
                }
                break;
            case 2:
                if (seniority == SEN_JUNIOR) {
                    salary = 607 * 1.60 + 150;
                }
                else if (seniority == SEN_SEMI_SENIOR) {
                    salary = 607 * 1.60 + 300;
                }
                else if (seniority == SEN_SENIOR) {
                    salary = 607 * 1.60 + 600;
                }
                break;
            default:
                System.out.println("INVALID OPTION");
                break;
        }
        return salary;
    }
}
