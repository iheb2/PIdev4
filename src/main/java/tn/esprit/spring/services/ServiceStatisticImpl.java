package tn.esprit.spring.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.models.*;

@Service
public class ServiceStatisticImpl implements ServiceStatistic {

	@Override
	public Statistic CreateStatistic(List<Client> products) {
		Statistic statistics = new Statistic();
		((Statistic) statistics).setCount(products.stream().count());
		((Statistic) statistics)
				.setAvg(BigDecimal.valueOf(products.stream().mapToDouble(t -> t.getScore()).average().orElse(0.0))
						.setScale(2, RoundingMode.HALF_UP));
		((Statistic) statistics)
				.setMin(BigDecimal.valueOf(products.stream().mapToDouble(t -> t.getScore()).min().orElse(0.0)).setScale(2,
						RoundingMode.HALF_UP));
		((Statistic) statistics)
				.setMax(BigDecimal.valueOf(products.stream().mapToDouble(t -> t.getScore()).max().orElse(0.0)).setScale(2,
						RoundingMode.HALF_UP));
		((Statistic) statistics).setSum(BigDecimal.valueOf(products.stream().mapToDouble(t -> t.getScore()).sum())
				.setScale(2, RoundingMode.HALF_UP));

		return (Statistic) statistics;
	}

}
