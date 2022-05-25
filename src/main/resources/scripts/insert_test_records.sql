INSERT INTO users (username, password, email, role, created_at) VALUES ('test_user', 'test123', 'test@test.com', 'USER', NOW());

INSERT INTO posts (user_id, title, content) VALUES (1, 'Babys First Post', 'Do not be alarmed. This is only a test.');

INSERT INTO Categories (name) VALUES ('test_tag');

INSERT INTO post_categories (post_id, categories_id) VALUES (1, 1);

SELECT * FROM users;

SELECT * FROM posts;
SELECT * FROM post_categories;