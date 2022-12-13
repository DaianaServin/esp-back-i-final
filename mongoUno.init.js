db.createUser(
    {
        roles: [
            {
                role: "readWrite",
                db: "catalogdevmongo"
            }
        ]
    }
);