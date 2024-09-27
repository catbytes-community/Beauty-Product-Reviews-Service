CREATE TABLE category (
                          id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          name VARCHAR(50) NOT NULL,
                          parent_id BIGINT,
                          CONSTRAINT fk_parent
                              FOREIGN KEY (parent_id)
                                  REFERENCES category(id)
                                  ON DELETE CASCADE
);

INSERT INTO category (name, parent_id) VALUES
-- Уровень 1
('Makeup', NULL),
('Skincare', NULL),
('Haircare', NULL),
('Fragrance', NULL),
('Bath & Body', NULL),
('Tools & Brushes', NULL),
('Other', NULL),

-- Уровень 2 (подкатегории Makeup)
('Foundation', 1),
('Lipstick', 1),
('Eyeshadow', 1),
('Blush', 1),
('Eyeliner', 1),
('Other', 1),

-- Уровень 2 (подкатегории Skincare)
('Cleansers', 2),
('Moisturizers', 2),
('Serums', 2),
('Toners', 2),
('Sunscreens', 2),
('Other', 2),

-- Уровень 2 (подкатегории Haircare)
('Shampoo', 3),
('Conditioner', 3),
('Hair Oil', 3),
('Hair Masks', 3),
('Styling Tools', 3),
('Other', 3),

-- Уровень 2 (подкатегории Fragrance)
('Perfume', 4),
('Body Spray', 4),
('Other', 4),

-- Уровень 2 (подкатегории Bath & Body)
('Body Wash', 5),
('Scrubs', 5),
('Lotions', 5),
('Other', 5),

-- Уровень 2 (подкатегории Tools & Brushes)
('Makeup Brushes', 6),
('Sponges', 6),
('Hair Dryers', 6),
('Other', 6);