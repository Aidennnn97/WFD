package com.dcu.wfd.common.vo;

// DTO(Data Transfer Object) or VO(Value Object)
// 계층간 데이터 교환을 위한 Java Beans.
// DB에서 받아온 데이터들을 매핑하기위한 객체.
// 일반적으로 logic 을 가지고 있지 않고, getter, setter만 있다. 

// Java Beans: 자바 관련 소프트웨어 개발에 있어 재사용이 가능한 표준 컴포넌트 모델
// 1. private field를 가지고 있다.  private field: 인스턴스 변수를 private로 가짐.
// 2. getter, setter로 필드에 접근한다. 
// 3. 생성자를 가진다. 
// 4. Serializable 구현.  Serializable: 자바 내부의 Object나 데이터를 외부에서도 사용할 수 있도록 하는 기술.

public class Test {
		private int testSeq;
		private String testTitle;
		private String testContent;
		
		public int getTestSeq() {
			return testSeq;
		}
		public void setTestSeq(int testSeq) {
			this.testSeq = testSeq;
		}
		public String getTestTitle() {
			return testTitle;
		}
		public void setTestTitle(String testTitle) {
			this.testTitle = testTitle;
		}
		public String getTestContent() {
			return testContent;
		}
		public void setTestContent(String testContent) {
			this.testContent = testContent;
		}		
}
