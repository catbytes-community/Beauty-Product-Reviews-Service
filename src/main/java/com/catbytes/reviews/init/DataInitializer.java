package com.catbytes.reviews.init;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private boolean complete = false;

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!complete) {
            insertCategoriesIfEmpty();
            complete = true;
        }
    }

    private void insertCategoriesIfEmpty() {
        if (!categoryRepository.existsBy()) {
            // Уровень 1
            Category makeup = new Category();
            makeup.setId(1L);
            makeup.setName("Makeup");
            categoryRepository.save(makeup);

            Category skincare = new Category();
            skincare.setId(2L);
            skincare.setName("Skincare");
            categoryRepository.save(skincare);

            Category haircare = new Category();
            haircare.setId(3L);
            haircare.setName("Haircare");
            categoryRepository.save(haircare);

            Category fragrance = new Category();
            fragrance.setId(4L);
            fragrance.setName("Fragrance");
            categoryRepository.save(fragrance);

            Category bathBody = new Category();
            bathBody.setId(5L);
            bathBody.setName("Bath & Body");
            categoryRepository.save(bathBody);

            Category toolsBrushes = new Category();
            toolsBrushes.setId(6L);
            toolsBrushes.setName("Tools & Brushes");
            categoryRepository.save(toolsBrushes);

            Category other = new Category();
            other.setId(7L);
            other.setName("Other");
            categoryRepository.save(other);

            // Уровень 2 (подкатегории Makeup)
            Category foundation = new Category();
            foundation.setId(8L);
            foundation.setName("Foundation");
            foundation.setParent(makeup);
            categoryRepository.save(foundation);

            Category lipstick = new Category();
            lipstick.setId(9L);
            lipstick.setName("Lipstick");
            lipstick.setParent(makeup);
            categoryRepository.save(lipstick);

            Category eyeshadow = new Category();
            eyeshadow.setId(10L);
            eyeshadow.setName("Eyeshadow");
            eyeshadow.setParent(makeup);
            categoryRepository.save(eyeshadow);

            Category blush = new Category();
            blush.setId(11L);
            blush.setName("Blush");
            blush.setParent(makeup);
            categoryRepository.save(blush);

            Category eyeliner = new Category();
            eyeliner.setId(12L);
            eyeliner.setName("Eyeliner");
            eyeliner.setParent(makeup);
            categoryRepository.save(eyeliner);

            Category makeupOther = new Category();
            makeupOther.setId(13L);
            makeupOther.setName("Other");
            makeupOther.setParent(makeup);
            categoryRepository.save(makeupOther);

            // Уровень 2 (подкатегории Skincare)
            Category cleansers = new Category();
            cleansers.setId(14L);
            cleansers.setName("Cleansers");
            cleansers.setParent(skincare);
            categoryRepository.save(cleansers);

            Category moisturizers = new Category();
            moisturizers.setId(15L);
            moisturizers.setName("Moisturizers");
            moisturizers.setParent(skincare);
            categoryRepository.save(moisturizers);

            Category serums = new Category();
            serums.setId(16L);
            serums.setName("Serums");
            serums.setParent(skincare);
            categoryRepository.save(serums);

            Category toners = new Category();
            toners.setId(17L);
            toners.setName("Toners");
            toners.setParent(skincare);
            categoryRepository.save(toners);

            Category sunscreens = new Category();
            sunscreens.setId(18L);
            sunscreens.setName("Sunscreens");
            sunscreens.setParent(skincare);
            categoryRepository.save(sunscreens);

            Category skincareOther = new Category();
            skincareOther.setId(19L);
            skincareOther.setName("Other");
            skincareOther.setParent(skincare);
            categoryRepository.save(skincareOther);

            // Уровень 2 (подкатегории Haircare)
            Category shampoo = new Category();
            shampoo.setId(20L);
            shampoo.setName("Shampoo");
            shampoo.setParent(haircare);
            categoryRepository.save(shampoo);

            Category conditioner = new Category();
            conditioner.setId(21L);
            conditioner.setName("Conditioner");
            conditioner.setParent(haircare);
            categoryRepository.save(conditioner);

            Category hairOil = new Category();
            hairOil.setId(22L);
            hairOil.setName("Hair Oil");
            hairOil.setParent(haircare);
            categoryRepository.save(hairOil);

            Category hairMasks = new Category();
            hairMasks.setId(23L);
            hairMasks.setName("Hair Masks");
            hairMasks.setParent(haircare);
            categoryRepository.save(hairMasks);

            Category stylingTools = new Category();
            stylingTools.setId(24L);
            stylingTools.setName("Styling Tools");
            stylingTools.setParent(haircare);
            categoryRepository.save(stylingTools);

            Category haircareOther = new Category();
            haircareOther.setId(25L);
            haircareOther.setName("Other");
            haircareOther.setParent(haircare);
            categoryRepository.save(haircareOther);

            // Уровень 2 (подкатегории Fragrance)
            Category perfume = new Category();
            perfume.setId(26L);
            perfume.setName("Perfume");
            perfume.setParent(fragrance);
            categoryRepository.save(perfume);

            Category bodySpray = new Category();
            bodySpray.setId(27L);
            bodySpray.setName("Body Spray");
            bodySpray.setParent(fragrance);
            categoryRepository.save(bodySpray);

            Category fragranceOther = new Category();
            fragranceOther.setId(28L);
            fragranceOther.setName("Other");
            fragranceOther.setParent(fragrance);
            categoryRepository.save(fragranceOther);

            // Уровень 2 (подкатегории Bath & Body)
            Category bodyWash = new Category();
            bodyWash.setId(29L);
            bodyWash.setName("Body Wash");
            bodyWash.setParent(bathBody);
            categoryRepository.save(bodyWash);

            Category scrubs = new Category();
            scrubs.setId(30L);
            scrubs.setName("Scrubs");
            scrubs.setParent(bathBody);
            categoryRepository.save(scrubs);

            Category lotions = new Category();
            lotions.setId(31L);
            lotions.setName("Lotions");
            lotions.setParent(bathBody);
            categoryRepository.save(lotions);

            Category bathBodyOther = new Category();
            bathBodyOther.setId(32L);
            bathBodyOther.setName("Other");
            bathBodyOther.setParent(bathBody);
            categoryRepository.save(bathBodyOther);

            // Уровень 2 (подкатегории Tools & Brushes)
            Category makeupBrushes = new Category();
            makeupBrushes.setId(33L);
            makeupBrushes.setName("Makeup Brushes");
            makeupBrushes.setParent(toolsBrushes);
            categoryRepository.save(makeupBrushes);

            Category sponges = new Category();
            sponges.setId(34L);
            sponges.setName("Sponges");
            sponges.setParent(toolsBrushes);
            categoryRepository.save(sponges);

            Category hairDryers = new Category();
            hairDryers.setId(35L);
            hairDryers.setName("Hair Dryers");
            hairDryers.setParent(toolsBrushes);
            categoryRepository.save(hairDryers);

            Category toolsBrushesOther = new Category();
            toolsBrushesOther.setId(36L);
            toolsBrushesOther.setName("Other");
            toolsBrushesOther.setParent(toolsBrushes);
            categoryRepository.save(toolsBrushesOther);
            LOG.info("Categories have been inserted into the database.");

        } else {
            LOG.info("Categories already exist in the database.");
        }
    }
}