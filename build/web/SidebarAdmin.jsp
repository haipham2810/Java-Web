
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="HomePageAdmin">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>

                <div class="sb-sidenav-menu-heading">Action</div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                   data-bs-target="#collapseLayoutss" aria-expanded="false" aria-controls="collapseLayoutss">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    List Borrow
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse show" id="collapseLayoutss" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="ListBorrowAdmin?action=processing">Processing</a>
                        <a class="nav-link" href="ListBorrowAdmin?action=borrowed">Borrow</a>
                        <a class="nav-link" href="ListBorrowAdmin?action=returned">Returned</a>
                    </nav>
                </div>

                <a class="nav-link" href="ListBook">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    List Book
                </a>
                <c:if test="${sessionScope.roleId == 1 or sessionScope.roleId == 3}">
                    <a class="nav-link" href="ListUser">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        List User
                    </a>
                    <a class="nav-link" href="ListEmployee">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        List Employee
                    </a>
                </c:if>

                <a class="nav-link" href="ViewFeedback">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Feedback
                </a>
                <a class="nav-link" href="ListCategory">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Category
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            ${sessionScope.username}
        </div>
    </nav>
</div>
