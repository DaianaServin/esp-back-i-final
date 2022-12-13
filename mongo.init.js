db.createUser(
    {
        roles: [
            {
                role: "readWrite",
                db: "seriedevmongo"
            }
        ]
    }
);