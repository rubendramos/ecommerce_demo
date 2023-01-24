package com.example.ecommerceprice;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ecommerceprice.client.BrandClient;
import com.example.ecommerceprice.entity.Price;
import com.example.ecommerceprice.entity.PriceId;
import com.example.ecommerceprice.exceptions.EcommercePriceException;
import com.example.ecommerceprice.modelo.Brand;
import com.example.ecommerceprice.repository.PriceRepository;
import com.example.ecommerceprice.service.PriceService;
import com.example.ecommerceprice.service.PriceServiceImpl;


@SpringBootTest
class EcommercePriceServiceMockTest {
	
	private static final String ERROR_INICIALIZE_DATA = "Error initializing data. Error = %s.";
	Logger logger = LoggerFactory.getLogger(EcommercePriceServiceMockTest.class);
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Mock
	private PriceRepository priceRepository;
	
	@Mock
	BrandClient brandClient;

	
	private PriceService priceService;

	@BeforeEach
	public void setUp() {

		Long productId = 3535L;
		Long brandId = 1L;
		String brandDescription = "ZARA";
		
		try {
		
			MockitoAnnotations.openMocks(this);
			priceService = new PriceServiceImpl(brandClient,priceRepository);

			Brand brand = Brand.builder().id(brandId).description(brandDescription).build();		
			ArrayList<Price> priceList = inicializePriceList(productId,brandId, brandDescription, formatter);
	
			String stringEfectiveDate = "2020-06-15 10:00:00";
			Date efectiveDate = formatter.parse(stringEfectiveDate);
	
			Mockito.when(priceRepository.findPriceByPriceId(productId, brandId, efectiveDate)).thenReturn(priceList);
			Mockito.when(brandClient.getBrandById(1L)).thenReturn(new ResponseEntity(brand, HttpStatus.OK));
		
		}catch (Exception e) {
			logger.error(String.format(ERROR_INICIALIZE_DATA, e));
		}

	}

	@Test
	void whenAskForPriceProduct_thenRetrieveUniqueCorrectPrice() {
		Long productId = 3535L;
		Long brandId = 1L;
		Price result = null;
		Price espectedResult = null;
		String brandDescription = "ZARA";
		
		try {
			
			String stringEfectiveDate = "2020-06-15 10:00:00";
			Date efectiveDate = formatter.parse(stringEfectiveDate);

			espectedResult = inicializeExpectedResult(3535L, 1L, brandDescription, formatter);
			result = priceService.getProductPriceByBrandAndDate(productId,brandId, efectiveDate);

		} catch (EcommercePriceException | ParseException e) {
			logger.error(String.format(ERROR_INICIALIZE_DATA, e));
			assertThat(Boolean.TRUE).isFalse();
		}
		assertThat(Objects.equals(result, espectedResult)).isTrue();
	}

	/**
	 * Inicialize Price's List
	 * 
	 * @return
	 */
	private ArrayList<Price> inicializePriceList(Long productId ,Long brandId, String brandDescripton, SimpleDateFormat formatter) {
		

		ArrayList<Price> priceList = new ArrayList<>();

		String stringStartDate1 = "2020-06-14 00:00:00";
		String stringEndDate1 = "2020-06-16 00:00:00";

		String StringStartDate2 = "2020-06-15 00:00:00";
		String stringEndDate2 = "2020-06-16 00:00:00";


		try {

			Date startDate1 = formatter.parse(stringStartDate1);
			Date startDate2 = formatter.parse(StringStartDate2);
			Date endDate1 = formatter.parse(stringEndDate1);
			Date endDate2 = formatter.parse(stringEndDate2);
			Brand brand = Brand.builder().id(brandId).description(brandDescripton).build();

			PriceId priceId1 = PriceId.builder().productId(productId).brandId(brandId).priceList(1L).brand(brand).build();

			PriceId priceId2 = PriceId.builder().productId(productId).brandId(brandId).priceList(2L).brand(brand).build();
			

			Price testPrice1 = Price.builder().priceId(priceId1).price(5.25D).currency("EU").startDate(startDate1)
					.endDate(endDate1).priority(1).build();

			Price testPrice2 = Price.builder().priceId(priceId2).price(15.25D).currency("EU").startDate(startDate2)
					.priority(0).endDate(endDate2).build();

			priceList.add(testPrice1);
			priceList.add(testPrice2);

		} catch (ParseException e) {
			logger.error(String.format(ERROR_INICIALIZE_DATA, e.getMessage()));
		}

		return priceList;
	}
	

	/**
	 * Inicialize Expected result (Price)
	 * 
	 * @return
	 */
	private Price inicializeExpectedResult(Long productId ,Long brandId, String brandDescripton, SimpleDateFormat formatter) {
		Price price = null;

		String stringStartDate1 = "2020-06-14 00:00:00";
		String stringEndDate1 = "2020-06-16 00:00:00";

		try {

			Date startDate1 = formatter.parse(stringStartDate1);
			Date endDate1 = formatter.parse(stringEndDate1);
			Brand brand = Brand.builder().id(brandId).description(brandDescripton).build();
			PriceId priceId = PriceId.builder().productId(productId).brandId(brandId).priceList(1L).brand(brand).build();


			price = Price.builder().priceId(priceId).price(5.25D).currency("EU").startDate(startDate1)
					.endDate(endDate1).priority(1).build();

		} catch (ParseException e) {
			logger.error(String.format(ERROR_INICIALIZE_DATA, e.getMessage()));

		}

		return price;
	}	
}
