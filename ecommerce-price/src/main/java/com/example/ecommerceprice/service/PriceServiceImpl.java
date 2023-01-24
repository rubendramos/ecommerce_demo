package com.example.ecommerceprice.service;



import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.ecommerceprice.client.BrandClient;
import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.exceptions.EcommercePriceException;
import com.example.ecommerceprice.exceptions.NonUniquePriceListException;
import com.example.ecommerceprice.modelo.Brand;
import com.example.ecommerceprice.repository.PriceRepository;
import com.example.ecommerceprice.utils.PricePriorityComparator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
	
	Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

	private static final int FIRST_ELEMENT_IN_LIST = 0;
	private static final int MINIMUN_SIZE_IN_LIST = 1;
	private static final String ERROR_DUPLICATE_TARIFF = "Price list are not unique";
	private static final String ERROR_RETRIEVIG_PRICE = "Error retrieving Price. Error = %s";


	private final BrandClient brandClient;
	private final PriceRepository priceRepositoty;


		
	@Override
	public List<Price> getAllPrices() throws EcommercePriceException {
		List<Price> priceList = null;

		try {

			priceList = priceRepositoty.findAll();

		} catch (Exception e) {			
			throw new EcommercePriceException(e, new Object[] {ERROR_RETRIEVIG_PRICE});
		}

		return priceList;
	}

	@Override
	public Price getProductPriceByBrandAndDate(Long productId, Long brandId, Date date) throws EcommercePriceException {
		List<Price> priceList = null;
		Price price = null;

		try {

			priceList = priceRepositoty.findPriceByPriceId(productId, brandId, date);
			price = getMaxPriorityPriceList(priceList);
			price = setBrandToPrice(price);

		} catch (Exception e) {			
			throw new EcommercePriceException(e, new Object[] { ERROR_RETRIEVIG_PRICE , productId, brandId, date });
		}

		return price;
	}

	/**
	 * Retrieves max priority priceList
	 * 
	 * @param priceList
	 * @return
	 */
	private Price getMaxPriorityPriceList(List<Price> priceList) throws EcommercePriceException {
		Price price = null;
		PricePriorityComparator pricePriorityComparator = new PricePriorityComparator();

		try {

			if (priceList != null) {
				Boolean areThereDuplicatePrices = areThereDuplicatePrices(priceList, pricePriorityComparator);
				if (priceList.size() > MINIMUN_SIZE_IN_LIST && Boolean.TRUE.equals(!areThereDuplicatePrices)) {
					Collections.sort(priceList, pricePriorityComparator.reversed());
				}
				price = priceList.get(FIRST_ELEMENT_IN_LIST);
			}

		} catch (Exception e) {
			logger.error(String.format(ERROR_DUPLICATE_TARIFF, price));
			throw new EcommercePriceException(e, new Object[] { priceList });
		}

		return price;
	}

	/**
	 * Check if are there duplicates prices using.
	 * 
	 * @param priceLits
	 * @return
	 */
	public Boolean areThereDuplicatePrices(final List<Price> priceLits, Comparator<Price> comparator)
			throws NonUniquePriceListException {
		Boolean thereAreDuplicatesPrice = Boolean.FALSE;
		Set<Price> priceSet = new TreeSet<>(comparator);
		
		for (Price price : priceLits) {
			if (!priceSet.add(price)) {
				throw new NonUniquePriceListException(ERROR_DUPLICATE_TARIFF, new Object[] { price });
			}
		}
			
		return thereAreDuplicatesPrice;
	}

	/**
	 * Set Brand to Price
	 * 
	 * @param price
	 * @return
	 */
	private Price setBrandToPrice(Price price) {
		Long brandId = price.getPriceId().getBrandId();
		price.getPriceId().setBrand(this.getBrand(brandId));
		return price;
	}

	/**
	 * Retrieves {@link Brand} by a id. If not exits return null.
	 * 
	 * @param brandId
	 * @return
	 * @throws EcommerceServiceException
	 */
	private Brand getBrand(Long idBrand) {
		return brandClient.getBrandById(idBrand).getBody();

	}

}
