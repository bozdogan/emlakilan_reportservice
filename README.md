# Emlak Ilan Sistemi - PropertyService

[main project repo](https://github.com/bozdogan/emlakilan)

Checks JWT provided in `Authentication` header before doing any operation

## API Endpoints
- `GET /api/properties` lists all public property ads
- `GET /api/properties/:id` shows property ad with given id
- `POST /api/properties` creates new property ad
- `PUT /api/properties/:id` updates a property ad
- `DELETE /api/properties/:id` deletes a property ad
- 

- `GET /api/properties/latest` Lists latest 10 approved property ads
- <small>*ADMIN*</small> `GET /api/list-all`  
- <small>*ADMIN*</small> `GET /api/list-pending` 
- <small>*ADMIN*</small> `GET /api/list-rejected`
- <small>*ADMIN*</small> `GET /api/accept/:id`
- <small>*ADMIN*</small> `GET /api/reject/:id`
