import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planner {
	private static Map<Integer, Integer> tablePages = new HashMap<Integer, Integer>();
	PhysicalMemory phis = new PhysicalMemory();
	VirtualMemory vir = new VirtualMemory();
	Page[] massiv = phis.getPhisMem();
	ArrayList<Page> v = vir.getVirMem();

	public Page Request(int idVir, int base, int lim) {
		if (base <= idVir & idVir < base + lim) {
			if (tablePages.containsKey(idVir)) {
				using(tablePages.get(idVir));
				return phis.getPhisPage(tablePages.get(idVir));
			} else {
				int freePlace = findFree();
				if (freePlace != -1) {
					if (phis.setPhisPage(vir.getPage(idVir), findFree())) {
						tablePages.put(idVir, freePlace);
						using(tablePages.get(idVir));
						return phis.getPhisPage(tablePages.get(idVir));
					} else {
						System.out.println("Ioeaea i?e ioia?a?aiee");
						return null;
					}
				} else {
					int idPhis = choosePage();
					findKey(idPhis);
					if (phis.setPhisPage(vir.getPage(idVir), idPhis)) {

						tablePages.put(idVir, idPhis);
						using(tablePages.get(idVir));
						return phis.getPhisPage(tablePages.get(idVir));
					} else {
						System.out.println("Ioeaea i?e ioia?a?aiee");
						return null;
					}
				}
			}
		} else {
			System.out.println("Ioeaea ainooia");
			return null;
		}
	}

	public void findKey(int idPhis) {
		for (Integer vals : tablePages.values()) {
			if (vals.equals(idPhis)) {

				tablePages.remove(vals);

				return;
			}
		}
	}

	private int findFree() {
		Page[] pages = phis.getPhisMem();
		for (int i = 0; i < phis.getSize(); i++) {
			if (pages[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private void using(int idVir) {
		for (int i = 0; i < phis.getSize(); i++) {
			Page res = phis.getPhisPage(i);
			if (res != null) {
				if (i == idVir) {
					res.use();
				}
				System.out.print(" ");
			}
		}
	}

	private int choosePage() {
		Page[] pages = phis.getPhisMem();
		int res = 0;
		int minFrequency = pages[res].getFrequency();
		for (int i = 1; i < phis.getSize(); i++) {
			if (pages[i].getFrequency()< minFrequency) {
				res = i;
				minFrequency = pages[i].getFrequency();
			}
		}
		Page p = phis.getPhisPage(res);
		System.out.println();
		System.out.println("Aoaao aua?o?aia no?aieoa  " + p.getData());

		return res;
	}
}