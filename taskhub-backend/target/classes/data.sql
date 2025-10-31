-- -- Roles
-- INSERT INTO roles (role_name, role_description) VALUES ('Admin', 'Administrator');
-- INSERT INTO roles (role_name, role_description) VALUES ('Developer', 'Developer');
-- INSERT INTO roles (role_name, role_description) VALUES ('Manager', 'Project');

-- INSERT INTO users (username, email, user_password, created_at, updated_at)
-- VALUES ('Harini', 'harini@example.com', '$2a$12$qoaYZcNf4NR6Yr5QPcL7wOpzRNpZ3IPXOe8adiyhK.CBxrkP3tWyi', NOW(), NOW());

-- INSERT INTO users (username, email, user_password, created_at, updated_at)
-- VALUES ('John', 'john@example.com', '$2a$12$qoaYZcNf4NR6Yr5QPcL7wOpzRNpZ3IPXOe8adiyhK.CBxrkP3tWyi', NOW(), NOW());

-- INSERT INTO users (username, email, user_password, created_at, updated_at)
-- VALUES ('Alice', 'alice@example.com', '$2a$12$qoaYZcNf4NR6Yr5QPcL7wOpzRNpZ3IPXOe8adiyhK.CBxrkP3tWyi', NOW(), NOW());


-- -- ===============================
-- -- USER_ROLE TABLE
-- -- ===============================
-- INSERT INTO user_role (user_id, role_id) VALUES (1, 1); -- Admin
-- INSERT INTO user_role (user_id, role_id) VALUES (2, 2); -- Manager
-- INSERT INTO user_role (user_id, role_id) VALUES (3, 3); -- User

-- -- Example Labels
-- INSERT INTO labels (label_name) VALUES ('To Do');
-- INSERT INTO labels (label_name) VALUES ('In Progress');
-- INSERT INTO labels (label_name) VALUES ('In Review');
-- INSERT INTO labels (label_name) VALUES ('Done');

-- INSERT INTO teams (team_name, created_at)
-- VALUES ('Core Dev Team', NOW());

-- INSERT INTO teams (team_name, created_at)
-- VALUES ('UI/UX Team', NOW());

-- -- ===============================
-- -- TEAM_USER TABLE
-- -- ===============================
-- INSERT INTO team_user (team_id, user_id)
-- VALUES (1, 2); -- Manager in Core Dev Team

-- INSERT INTO team_user (team_id, user_id)
-- VALUES (2, 3); -- User1 in UI/UX Team


-- INSERT INTO task ( task_title, task_description, user_id, label_id, priority, start_date, due_date, created_at, updated_at)
-- VALUES ('Setup Backend', 'Create Spring Boot APIs', 2, 1, 'High', '2025-10-01', '2025-10-10', CURDATE(), CURDATE());

-- INSERT INTO task ( task_title, task_description, user_id, label_id, priority, start_date, due_date, created_at, updated_at)
-- VALUES ('Frontend UI', 'Develop Angular dashboard', 2, 2, 'Medium', '2025-10-05', '2025-10-15', CURDATE(), CURDATE());

-- INSERT INTO task (task_title, task_description, user_id, label_id, priority, start_date, due_date, created_at, updated_at)
-- VALUES ('Landing Page', 'Design new homepage', 3, 1, 'Low', '2025-09-20', '2025-10-05', CURDATE(), CURDATE());


