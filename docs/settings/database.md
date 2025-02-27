# 데이터베이스

> 아직 DB를 어떤식으로 사용할지는 결정하지 않았는데,
>
> Maria DB 11에다가 미리 만들어두어보자 😅



### 데이터 베이스와 사용자 생성

#### 개발환경 DB
* 💡 실제 환경에 반영할일이 있을지는 모르지만 그때는 vault에서 암호를 바꾸면 되겠다. 😅

```sql
CREATE DATABASE lotto DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'lottouser'@'localhost' IDENTIFIED BY 'lottouser';

CREATE USER 'lottouser'@'%' IDENTIFIED BY 'lottouser';

GRANT ALL PRIVILEGES ON lotto.* TO 'lottouser'@'localhost';

GRANT ALL PRIVILEGES ON lotto.* TO 'lottouser'@'%';
```

