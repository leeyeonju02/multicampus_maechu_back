# WorkSpace : 운동인을 위한 추천 및 매칭 플랫폼

<img src="https://img.notionusercontent.com/s3/prod-files-secure%2F4dd29acc-ae82-4711-859e-7887147375b9%2Fdf00d98a-d075-4583-a4c0-327be7b574d7%2FIMG_1056.jpg/size/w=2000?exp=1741764235&sig=AxPGPZYVC-4NBQE2xFb0kBZhbjqwagFREqm2zk3fq18"/></a>



### 멀티캠퍼스 25기 최종프로젝트 - 추천 및 매칭 서비스 team

- 개발 기간 : 2024.09.23 ~ 2024.11.28
  <br/>
  <br/>
  
## 기획 계기

2023년 mz세대의 운동트렌드에 따르면 최근 6개월 내 주기적으로 운동하는 비율은 91.2%, 인스타그램 오운완 해시테그 사용 539만 등
운동에 대한 관심이 높아지며 '스포츠 자아'를 가진 사람들이 꾸준히 늘어나는 추세이다.
<br/>
이에 따라 수많은 운동인들이 꾸준한 운동 동기, 운동에 대한 정보 공유, 다양한 운동인들과의 소통의 필요성을 느끼고 있다.
운동종목, 지역, 나이, 운동 수준에 상관없이 다양한 사람들이 편리하게 사용할 수 있는 서비스에 대한 부족함을 느껴 이를 개선하기 위해 기획되었다.

<br/>
<br/> 


## 팀 소개

<table>
  <tr >
    <td align="center" width="150px" >
      <a href="https://github.com/leeyeonju02"><img src="https://avatars.githubusercontent.com/u/85239317?v=4"/></a>
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/Yuminyumin"><img src="https://avatars.githubusercontent.com/u/102581107?v=4"/></a>
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/seokhun11"><img src="https://avatars.githubusercontent.com/u/143687743?v=4"/></a>
    </td>
  </tr>
  <tr>
    <td align="center" width="150px" >
      <a href="https://github.com/sanghee01/"><strong>이연주</strong></a><br>리더, fullstack
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/maybeaj/"><strong>신유민</strong></a><br>backend
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/nemokoala/"><strong>한석훈</strong></a><br>fullstack
    </td>
  </tr>
</table>
<table>
  <tr >
    <td align="center" width="150px" >
      <a href="https://github.com/ddayhyun"><img src="https://avatars.githubusercontent.com/u/115991771?v=4"/></a>
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/camelliaseolwang"><img src="https://avatars.githubusercontent.com/u/180196751?v=4"/></a>
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/w00jinLee"><img src="https://avatars.githubusercontent.com/u/122732558?v=4"/></a>
    </td>
     <td align="center" width="150px" >
      <a href="https://github.com/anfrk-full"><img src="https://avatars.githubusercontent.com/u/179059714?v=4"/></a>
    </td>
  </tr>
  <tr>
    <td align="center" width="150px" >
      <a href="https://github.com/ddayhyun"><strong>김동현</strong></a><br>backend
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/camelliaseolwang"><strong>설효진</strong></a><br>fullstack
    </td>
    <td align="center" width="150px" >
      <a href="https://github.com/w00jinLee"><strong>이우진</strong></a><br>front
    </td>
      <td align="center" width="150px" >
      <a href="https://github.com/anfrk-full"><strong>강은종</strong></a><br>front
    </td>
  </tr>
</table>

<br/>
<br/> 

## 아키텍처

![logo](/src/main/resources/readme/dia.png)

- 프론트엔드는 typescript와 react를 활용해 구현을 하였으며 Styled-componentes를 적용해 스타일을 구현했습니다. 유저 정보에 대한 상태관리는 React-context api를 활용하며
  axios를 통한 백엔드와 통신을 하였습니다
- 백엔드는 springboot와 gradle을 사용하였고 mybatis를 활용해 orm을 적용하였고, 디비는 mysql을 사용했습니다.
- 배포는 ncp(naver cloud platform) 에서 제공해주는 cloud db for mysql을 활용해 데이터베이스 배포를 하였으며, 이미지와 같은 정적리소스는 ncp의 object storage를 활용해 저장하였습니다.
- 백엔드와 프론트 서버는 https 인증서 자동발급과 git 저장소를 활용하는 cloudtype을 활용해 배포하였습니다.
- 사용자 인증/인가 시 jwt를 활용한 토큰을 발급하였고 브라우저의 localstorage에 토큰을 저장하는 방식을 사용하며, goole smtp 이메일 인증을 적용하였습니다.
- 운동 및 식단기록, 추천 기능은 공공데이터 포털에서 제공해주는 open api를 사용하였습니다.

  
<br/>
<br/> 


## erd 
![logo](/src/main/resources/readme/erd.png)

<br/> 
<br/> 

## 주요 기능 
#### 1. 사용자 맞춤형 서비스 
- 회원가입 필수 정책 : 사용자 맞춤형 운동 프로필로 최적화된 정보 매칭 및 추천
- jwt + spring security

#### 2. 맞춤형 운동/식단 기록 및 추천 
- 사용자 운동 목표에 따른 추천 칼로리 제안 : 해리스-베네딕트 공식(BMR - 기초대사량) + 활동 강도에 따른 TDEE(권장칼로리) 
- 운동 기록 시 종목, 기구에 따른 소모 칼로리 계산 : 한국증진개발원_보건소의 종목별 칼로리 open api + MET 계산법
- 음식별 섭취 칼로리 계산(탄/단/지 기록) : 식품의약품안전처 식품영양정보 open api
- 식재료, 요리 난이도, 추천 칼로리, 운동 목표 기반의 식단 추천 : Gemini api

#### 3. 운동 크루 
- 다양한 운동 목표, 종목 기반한 자유로운 운동 크루 생성
- 사용자의 지역 기반의 운동 크루 추천 (시군구 -> 시도 순서의 지역 기반의 크루 추천)
- 크루 내 커뮤니티 기능(소속감, 운동 동기 상승)
- 크루 내 배틀 기능 (자유로운 배틀 신청, 뱃지 획득)

#### 4. 운동 종목 별 커뮤니티 
- 사용자 관심 종목 3가지 순위별 인기 게시물 추천 (가중치 부여한 알고리즘)
- 운동에 대한 키워드 해시테그 활용
- 뱃지 가시화를 통한 커뮤니티 내 사용자들의 영향력 부여

#### 5. 뱃지 기능 
- 개인 뱃지 : 운동 및 식단 기록, 게시물 작성, 댓글 작성에 따른 포인트 부여 
- 크루 뱃지 : 크루 내 배틀 승리 시 점수 획득

<br/> 
<br/> 

## frontend git link
https://github.com/maechu-egg/multicampus_maechu_front

<br/>
<br/>

## 시연 영상 
https://www.youtube.com/watch?v=roMHHzFvCP4
