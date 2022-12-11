db.createUser(
    {
        user: "usrsriemongo",
        pwd: "pwdseriemongo",
        roles: [
            {
                role: "readWrite",
                db: "seriedevmongo"
            }
        ]
    }
);