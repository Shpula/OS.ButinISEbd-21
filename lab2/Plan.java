import java.util.ArrayList;
import java.util.List;


public class Plan {
	private ArrayList<Potok> potoks = new ArrayList<>();
	public static final int QUANT = 5;
	public Plan(ArrayList<Process> processes) {
		for (int i=0; i < processes.size(); i++)
        {
            List<Potok> potok= processes.get(i).getlist();
            for (var t : potok)
            {
                potoks.add(t);
            }
        }

	}
	
	public void startP(int quant) {
		while (!potoks.isEmpty()) {
			for (int i = 0; i < potoks.size(); i++) {
				quant = QUANT;
				while (potoks.get(i).isTime() && quant > 0) {
					print(" a i?ioanna",i);
					potoks.get(i).minusTime();
					quant--;
					if (quant == 0 && potoks.get(i).getOperatingTime() != 0) {
						print(" i?einoaiiaeai",i);
						System.out.println();
						--i;
						
						break;
					}
					if (potoks.get(i).getOperatingTime() == 0) {
						print(" caaa?oai",i);
						System.out.println();
						potoks.remove(i);
						--i;
						break;
					}
				}
			}
		}
	}
	public void print() {
		for (Potok t : potoks) {
			System.out.println(t.getpid() + " " + t.getId() + " I?ei?eoao: "
					+ t.getprioritet() + " A?aiy: " + t.getOperatingTime());
		}
	}
	public void print(String str,int i){
		System.out.println(potoks.get(i).getpid() + " "
				+ potoks.get(i).getId() + " I?ei?eoao: "
				+ potoks.get(i).getprioritet() + " A?aiy: "
				+ potoks.get(i).getOperatingTime()
				+ str);
	}

}