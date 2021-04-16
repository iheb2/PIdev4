package tn.esprit.spring.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.AmalWacelGhada;
import tn.esprit.spring.model.StateT;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.model.TypeT;
import tn.esprit.spring.model.mode;
import tn.esprit.spring.repo.TransactionRepository;
import tn.esprit.spring.repo.a;

import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;


@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	TransactionRepository trsansactionRepository ;

	@Autowired
	a aw ;

	@Override
	public Transaction updateTransaction(Transaction p) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Transaction retrieveTransaction(String Id) {
		
		return trsansactionRepository.findById(Long.parseLong(Id)).orElse(null);
	}
	@Override
	public List<Transaction> retrieveAllTransactions() {
		return (List<Transaction>) trsansactionRepository.findAll();
	}

	
	@Override
	public Transaction addTransaction(Transaction t) {
		return trsansactionRepository.save(t);
	}

	@Override
	public void deleteTransaction(Long id_t) {
		trsansactionRepository.deleteById((long) id_t);
	
	}
	/***************************************/
	/*int days = Days.daysBetween(startDate, endDate).getDays();
	List dates = new ArrayList(days); 
	// Set initial capacity to `days`. 
	for (int i=0; i < days; i++) { LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i); 
	dates.add(d); }

*/
//ajouter toute les transaction a effectuer pour chaque credit lors de son attribution
	//ajouter toute les transaction a effectuer pour chaque credit lors de son attribution
		@Override
		public void retrieveCreditTransaction(AmalWacelGhada a,mode m) {
			java.util.Date  d = a.getAttribDate();
			LocalDate l= d.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
				int n= a.getNbmois();
				List<Transaction> list =new ArrayList<Transaction>();
				for (int i=1;i<=n;i++)
				{
				Date s=java.sql.Date.valueOf(l.plusMonths(i));
				Transaction T= new Transaction();
				T.setAmount(a.getPortionamount());
				T.setDateT(s);
				T.setNb(0);
				T.setAc(a);
				T.setMode(m);
				T.setState(StateT.non);
				T.setType(TypeT.Credit);
				trsansactionRepository.save(T);
				list.add(T);}
				
		}
////////////////payment automatique////////////////////////////////////
		@Override
	//	@Scheduled(fixedRate = 10000)
		public void payment_auto()
		{List<Transaction> list = (List<Transaction>) trsansactionRepository.findAll();
		Date d= new Date();
		for (Transaction i:list){
			if(i.getState()==StateT.non &&i.getType()==TypeT.Credit&&i.getMode()==mode.automatique&&i.getAmount()<=i.getAc().getSomme()&&i.getDateT().before(d))
			{	long diff;
				Date q = new Date();
				Date x = i.getDateT();

				if (x.before(q)) {
					long diffInMillies = Math.abs(x.getTime() - q.getTime());
					diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				} else {
					diff = 0;
				}
				i.setRetard(diff);
				i.setPenalite(penalite(diff) * i.getAmount());
				i.setDateP(q);
				i.setState(StateT.oui);
				i.setTotal(i.getAmount() + (i.getAmount() * penalite(diff)));
				i.setNb(i.getNb() + 1);
				i.setArgent(i.getAc().getSomme());
				trsansactionRepository.save(i);
			    AmalWacelGhada z = i.getAc();
			    double e = i.getAc().getSomme()-(i.getAmount() + (i.getAmount() * penalite(diff)));
			    z.setSomme(e);
			    aw.save(z);
		
			}}}
			
/////////////////////////////////payer en agence//////////////////////////////////////		
		// payer une tranche du crdit.//a l'agence (direct)
		@Override
		public String paycreditDirect(Long id_c) {
			List<Transaction> list = (List<Transaction>) trsansactionRepository.findAll();
			List<Transaction> list1 = new ArrayList<Transaction>();
			long diff;
			for (Transaction t : list) {
				Long a = t.getAc().getId();
				if (a == id_c && t.getState() == StateT.non) {
					list1.add(t);
				}
			}
			Transaction t = list1.get(0);
			Date q = new Date();
			Date x = t.getDateT();

			if (x.before(q)) {
				long diffInMillies = Math.abs(x.getTime() - q.getTime());
				diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			} else {
				diff = 0;
			}
			t.setRetard(diff);
			t.setPenalite(penalite(diff) * t.getAmount());
			t.setDateP(q);
			t.setState(StateT.oui);
			t.setMode(mode.direct);
			t.setTotal(t.getAmount() + (t.getAmount() * penalite(diff)));
			t.setNb(t.getNb() + 1);
			t.setArgent(t.getAc().getSomme());

			trsansactionRepository.save(t);
			return "le montant total a payer avec tous frais inclue est:" + t.getTotal() + "Dt";
		}		
		public double penalite(long diff) {
			double p;
			if (diff == 0) {
				p = 0;
			} else {
				if (diff <= 30 && diff > 0) {
					p = 0.0075;
				} else {
					if (diff > 30 && diff <= 60) {
						p = 0.0125;
					} else {
						p = 0.025;
					}
				}

			}
			return p;

		}
		// payer un tranche du credit de port a port
		@Override
		public String paycreditPortaPort(Long id_c) {
			List<Transaction> list = (List<Transaction>) trsansactionRepository.findAll();
			List<Transaction> list1 = new ArrayList<Transaction>();
			long diff;
			for (Transaction t : list) {
				Long a = t.getAc().getId();
				if (a == id_c && t.getState() == StateT.non) {
					list1.add(t);
				}
			}
			Transaction t = list1.get(0);
			Date q = new Date();
			Date x = t.getDateT();

			if (x.before(q)) {
				long diffInMillies = Math.abs(x.getTime() - q.getTime());
				diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			} else {
				diff = 0;
			}
			t.setRetard(diff);
			t.setPenalite(penalite(diff) * t.getAmount());
			t.setDateP(q);
			t.setState(StateT.oui);
			t.setMode(mode.de_port_a_port);
			t.setTotal(t.getAmount() + (t.getAmount() * penalite(diff)));
			t.setNb(t.getNb() + 1);
			t.setArgent(t.getAc().getSomme());
			trsansactionRepository.save(t);
			return "le montant total a payer avec tous frais inclue est:" + t.getTotal() + "Dt";
		}

//payer une tranche du crdit.
	@Override
	public Transaction paycredit(Long id_c) {
		List<Transaction> list =(List<Transaction>) trsansactionRepository.findAll();
		List<Transaction> list1 =new ArrayList<Transaction>();
		 
		for (Transaction t: list)
			{Long a=t.getAc().getId(); 
				if(a==id_c && t.getState()==StateT.non){list1.add(t);}
			}
		 Transaction t =list1.get(0);
		 t.setDateP(new Date());
		 t.setState(StateT.oui);
		 return trsansactionRepository.save(t);
	}
	//afficher toutes les transaction effectuer par compte
	@Override
	public List<Transaction> Historique(String id_c) {
		AmalWacelGhada s=aw.findById(Long.parseLong(id_c)).orElse(null);
		return s.getTransaction();
	}
	@Override
public void retrait(String id,double a)	//id account
{   AmalWacelGhada e = aw.findById(Long.parseLong(id)).orElse(null);
		if(a<=e.getSomme()){
			
			Transaction T= new Transaction();
			T.setAmount(a);
			T.setDateT(new Date());
			T.setNb(0);
			T.setAc(e);
			T.setType(TypeT.Debit);
			trsansactionRepository.save(T);
							  }}
	


	@Override
	public double paycreditparcart(Long id_c) {
		List<Transaction> list = (List<Transaction>) trsansactionRepository.findAll();
		List<Transaction> list1 = new ArrayList<Transaction>();
		long diff;
		for (Transaction t : list) {
			Long a = t.getAc().getId();
			if (a == id_c && t.getState() == StateT.non) {
				list1.add(t);
			}
		}
		Transaction t = list1.get(0);
		Date q = new Date();
		Date x = t.getDateT();

		if (x.before(q)) {
			long diffInMillies = Math.abs(x.getTime() - q.getTime());
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} else {
			diff = 0;
		}
		t.setRetard(diff);
		t.setPenalite(penalite(diff) * t.getAmount());
		t.setDateP(q);
		t.setState(StateT.oui);
		t.setMode(mode.par_carte);
		t.setTotal(t.getAmount() + (t.getAmount() * penalite(diff)));
		
		
		t.setArgent(t.getAc().getSomme());

		trsansactionRepository.save(t);
		return  t.getTotal();
	}




	

}
