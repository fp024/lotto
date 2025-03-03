# 로또 번호 연구

> 로또 번호를 연구해보자! 😊

## 프로젝트

### 어드민

* [lotto_admin](lotto_admin)

### 서비스

* lotto_web



### [지원 문서](docs)

> 프로젝트 문서 모음





## 기타

### 소스코드 포맷팅

* Prettier

  > 프로젝트 루트에서 다음 명령어 순서대로 실행
  >
  > ```sh
  > pnpm install
  > pnpm format
  > ```
  >
  > html, css, js 파일의 포멧팅은 Prettier로 수행한다.

* Java

  > IDE에서 설정한 Google Java Format을 사용한다.



### VSCode Java 개발 환경 지원

> IntelliJ를 쓰고, 빌드를 Gradle로 맞춰두면 build.gradle에 정의해둔 설정만으로도 다 해결이되어서, 문제가 없는데, 😅
>
> VSCode Java 환경은 IDE와 Gradle 통합이 완전하지 않아서인지? 해줘야할 일이 있었다. 
>
> 해줘야할 일들을 npm 스크립트로 처리했는데... 쉘/배치 스크립트로 만드는 것보다 왠지 훨씬 나은 것 같은 느낌이든다.. 👍

#### VScode Java 개발 환경을 위한 Boot 3 프로젝트 설정 초기화 지원 목적의 pnpm 스크립트

> 다음 명령 넣어주면 VSCode에서 쓰기 편하게 초기화해준다.
>
> ```sh
>pnpm init-project
> ```

* **add-javac-parameters-option**
  * 컴파일러 옵션으로 `-parameters` 가 적용되도록 `${workspace}/.settings/org.eclipse.jdt.core.prefs` 파일을 생성하고 관련된 컴파일러 옵션을 추가한다.
  
* **copy-mockito-jar**

  * 현재 Gradle 빌드 프로젝트에 디펜던시된 mockito-core-x.x.x.jar 파일을  `${workspace}/javaagent-libs` 경로에 복사한다.

* **init-test-jvm-options**

  * `.vscode/settings.json`에 java.test.config 설정을 구성한다.
    1. mockito-core JAR파일을 javaagent로 설정
    2. -Xshare:off VM 옵션 설정

* **init-project**

  * 앞의 모든 작업을 순서대로 실행하는 스크립트




### node를 버전업그레이드 해서 다시 설치했을 때.

다음 명령으로 pnpm이 사용가능하도록 설정해준다.

```sh
corepack enable
```



