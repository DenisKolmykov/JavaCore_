package less03;

public class Program {
    private static final int AMOUNT_CONTRACTORS = 9; // общее количество исполнителей


    public static void main(String[] args) {

        ContactorsCollection contractorList = new ContactorsCollection();
        contractorList.setAmountContractors(AMOUNT_CONTRACTORS);
        System.out.printf("Количество Workers = %d\n",contractorList.getWorkersAmount());
        System.out.printf("Количество Freelancer = %d\n",contractorList.getFreelancerAmount());
        System.out.println();

        contractorList.createContractorsCollections();

//        for(Contractor contractors : contractorList.getContractorsList()){
//            System.out.println(contractors);
//        }

        contractorList.printContactorsCollection();

        System.out.println("\nСортировка по уровню дохода:");
        contractorList.sortContractorsBySalary();
        contractorList.printContactorsCollection();


    }
}