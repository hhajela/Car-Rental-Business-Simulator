public class app
{
    public static void main(String[] args)
    {
        CustomerFactory cmf = new CustomerFactory();
        CarFactory crf = new CarFactory();
        AbstractStore store = new Store();
        Simulation s = new Simulation(1,cmf,crf,store);
        s.runSimulation();
    }
}
