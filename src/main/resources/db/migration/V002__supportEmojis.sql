ALTER TABLE text_entities CHANGE name name VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE text_entities CHANGE text text LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
