INSERT INTO roles(id, name)
VALUES (1 ,'ROLE_USER'),
       (2, 'ROLE_MODERATOR'),
       (3, 'ROLE_ADMIN');

INSERT INTO tenants (id, name, subdomain)
VALUES (1, 'Tenant 1', 'tenant1'),
       (2, 'Tenant 2', 'tenant2');

# user, password
# user1, user1
# user2, user2
INSERT INTO users (id, username, password)
VALUES (1, 'user1', '$2a$10$wFMJLxdXKGRa8lJO6k2DAOnW9HstAPoHecXUNkDyYNeaNnZJAz.hy'),
       (2, 'user2', '$2a$10$Z9/wLkmf5IwfjJqIQU6X.OBFg3TCBUyk3bdfgkGjU0.HI5kVibZxG');

INSERT INTO users_tenants (id,  tenant_id, user_id)
VALUES (1, 1, 1),
       (2, 2, 2);

INSERT INTO users_tenants_roles (user_tenant_id,  role_id)
VALUES (1, 2),
       (1, 3),
       (2, 1);


INSERT INTO items (id,  tenant_id, name)
VALUES (1, 1, 'Product 1 in Tenant 1'),
       (2, 1, 'Product 2 in Tenant 1'),
       (3, 2, 'Product 1 in Tenant 2'),
       (4, 2, 'Product 2 in Tenant 2');

