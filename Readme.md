## JobSphere-Profile-Service
This Repository holds Profile-service/controller/model.
Part of Microservice Architecture used for [Jobsphere](https://github.com/jobsphere).

-   ### Endpoints:

    -   #### Profile:

    	-   Create Profile `/profile`

    		| Method | Description                                                   |
    		| :----: | :------------------------------------------------------------ |
    		| `POST` | A **POST** request to this endpoint will create a new Profile |

    	-   Profile by ID `/profile/{profile_id}`

    		|  Method  | Description                                                                                |
    		| :------: | :----------------------------------------------------------------------------------------- |
    		|  `PUT`   | A **PUT** request with Profile ID to this endpoint will update the Profile.                |
    		|  `GET`   | A **GET** request with Profile ID to this endpoint will return profile details of that ID. |
    		| `DELETE` | A **DELETE** request with Profile ID will delete the specific profile.                     |
