

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Admin Page</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="./style/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    </head>

    <body class="sb-nav-fixed" style="overflow-x: hidden">
        <%@include file="./Navbar.jsp"  %>

        <div id="layoutSidenav">

            <!-- Sidebar  -->
            <%@include file="./SidebarAdmin.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <section>
                        <div class="container py-5">
                            <div class="row justify-content-center">
                                <div class="col-lg-8 ">
                                    <p class="text-danger">${mess}</p>
                                    <form action="AddCategoryNew" method="post" class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Category Id</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input type="number" name="category_id" required/>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Category Name</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input type="text" name="category_name" required/>
                                                </div>
                                            </div>
                                        </div>
                                        <input style="width: 20%" type="submit" class="btn btn-primary" value="Add Category"></input>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                </main>

                <!-- Footer -->
                <%@include file="./Footer.jsp"%>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script>
            window.addEventListener('DOMContentLoaded', event => {
                const sidebarToggle = document.body.querySelector('#sidebarToggle');
                if (sidebarToggle) {
                    sidebarToggle.addEventListener('click', event => {
                        event.preventDefault();
                        document.body.classList.toggle('sb-sidenav-toggled');
                        localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
                    });
                }

            });
        </script>

    </body>

</html>
