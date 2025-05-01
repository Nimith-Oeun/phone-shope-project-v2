package com.unimal.phone_shope_demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.unimal.phone_shope_demo.model.Brand;
import com.unimal.phone_shope_demo.repositery.BrandRepoSitery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BrandRepositoryTest {
	@Autowired
	private BrandRepoSitery brandRepository;

	@Test
	public void testFindByNameLike() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		Brand brand2 = new Brand();
		brand2.setName("Samsung");
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		//when
		List<Brand> brands = brandRepository.findByNameLike("%A%");
		
		//then
		assertEquals(1, brands.size());
		assertEquals("Apple", brands.get(0).getName());
		assertEquals(1, brands.get(0).getId());
	}
}