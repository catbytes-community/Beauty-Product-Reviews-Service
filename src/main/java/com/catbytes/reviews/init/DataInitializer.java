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
            makeup.setName("Makeup");
            categoryRepository.save(makeup);

            Category skincare = new Category();
            skincare.setName("Skincare");
            categoryRepository.save(skincare);

            Category haircare = new Category();
            haircare.setName("Haircare");
            categoryRepository.save(haircare);

            Category fragrance = new Category();
            fragrance.setName("Fragrance");
            categoryRepository.save(fragrance);

            Category bathBody = new Category();
            bathBody.setName("Bath & Body");
            categoryRepository.save(bathBody);

            Category toolsBrushes = new Category();
            toolsBrushes.setName("Tools & Brushes");
            categoryRepository.save(toolsBrushes);

            Category other = new Category();
            other.setName("Other");
            categoryRepository.save(other);

            // Уровень 2 (подкатегории Makeup)
            Category foundation = new Category();
            foundation.setName("Foundation");
            foundation.setParent(makeup);
            categoryRepository.save(foundation);

            Category lipstick = new Category();
            lipstick.setName("Lipstick");
            lipstick.setParent(makeup);
            categoryRepository.save(lipstick);

            Category eyeshadow = new Category();
            eyeshadow.setName("Eyeshadow");
            eyeshadow.setParent(makeup);
            categoryRepository.save(eyeshadow);

            Category blush = new Category();
            blush.setName("Blush");
            blush.setParent(makeup);
            categoryRepository.save(blush);

            Category eyeliner = new Category();
            eyeliner.setName("Eyeliner");
            eyeliner.setParent(makeup);
            categoryRepository.save(eyeliner);

            Category makeupOther = new Category();
            makeupOther.setName("Other");
            makeupOther.setParent(makeup);
            categoryRepository.save(makeupOther);

            // Уровень 2 (подкатегории Skincare)
            Category cleansers = new Category();
            cleansers.setName("Cleansers");
            cleansers.setParent(skincare);
            categoryRepository.save(cleansers);

            Category moisturizers = new Category();
            moisturizers.setName("Moisturizers");
            moisturizers.setParent(skincare);
            categoryRepository.save(moisturizers);

            Category serums = new Category();
            serums.setName("Serums");
            serums.setParent(skincare);
            categoryRepository.save(serums);

            Category toners = new Category();
            toners.setName("Toners");
            toners.setParent(skincare);
            categoryRepository.save(toners);

            Category sunscreens = new Category();
            sunscreens.setName("Sunscreens");
            sunscreens.setParent(skincare);
            categoryRepository.save(sunscreens);

            Category skincareOther = new Category();
            skincareOther.setName("Other");
            skincareOther.setParent(skincare);
            categoryRepository.save(skincareOther);

            // Уровень 2 (подкатегории Haircare)
            Category shampoo = new Category();
            shampoo.setName("Shampoo");
            shampoo.setParent(haircare);
            categoryRepository.save(shampoo);

            Category conditioner = new Category();
            conditioner.setName("Conditioner");
            conditioner.setParent(haircare);
            categoryRepository.save(conditioner);

            Category hairOil = new Category();
            hairOil.setName("Hair Oil");
            hairOil.setParent(haircare);
            categoryRepository.save(hairOil);

            Category hairMasks = new Category();
            hairMasks.setName("Hair Masks");
            hairMasks.setParent(haircare);
            categoryRepository.save(hairMasks);

            Category stylingTools = new Category();
            stylingTools.setName("Styling Tools");
            stylingTools.setParent(haircare);
            categoryRepository.save(stylingTools);

            Category haircareOther = new Category();
            haircareOther.setName("Other");
            haircareOther.setParent(haircare);
            categoryRepository.save(haircareOther);

            // Уровень 2 (подкатегории Fragrance)
            Category perfume = new Category();
            perfume.setName("Perfume");
            perfume.setParent(fragrance);
            categoryRepository.save(perfume);

            Category bodySpray = new Category();
            bodySpray.setName("Body Spray");
            bodySpray.setParent(fragrance);
            categoryRepository.save(bodySpray);

            Category fragranceOther = new Category();
            fragranceOther.setName("Other");
            fragranceOther.setParent(fragrance);
            categoryRepository.save(fragranceOther);

            // Уровень 2 (подкатегории Bath & Body)
            Category bodyWash = new Category();
            bodyWash.setName("Body Wash");
            bodyWash.setParent(bathBody);
            categoryRepository.save(bodyWash);

            Category scrubs = new Category();
            scrubs.setName("Scrubs");
            scrubs.setParent(bathBody);
            categoryRepository.save(scrubs);

            Category lotions = new Category();
            lotions.setName("Lotions");
            lotions.setParent(bathBody);
            categoryRepository.save(lotions);

            Category bathBodyOther = new Category();
            bathBodyOther.setName("Other");
            bathBodyOther.setParent(bathBody);
            categoryRepository.save(bathBodyOther);

            // Уровень 2 (подкатегории Tools & Brushes)
            Category makeupBrushes = new Category();
            makeupBrushes.setName("Makeup Brushes");
            makeupBrushes.setParent(toolsBrushes);
            categoryRepository.save(makeupBrushes);

            Category sponges = new Category();
            sponges.setName("Sponges");
            sponges.setParent(toolsBrushes);
            categoryRepository.save(sponges);

            Category hairDryers = new Category();
            hairDryers.setName("Hair Dryers");
            hairDryers.setParent(toolsBrushes);
            categoryRepository.save(hairDryers);

            Category toolsBrushesOther = new Category();
            toolsBrushesOther.setName("Other");
            toolsBrushesOther.setParent(toolsBrushes);
            categoryRepository.save(toolsBrushesOther);
            LOG.info("Categories have been inserted into the database.");

        } else {
            LOG.info("Categories already exist in the database.");
        }
    }
}