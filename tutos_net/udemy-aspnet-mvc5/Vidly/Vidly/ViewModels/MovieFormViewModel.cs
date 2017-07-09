using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using Vidly.Models;

namespace Vidly.ViewModels
{
    public class MovieFormViewModel
    {
        public IEnumerable<Genre> Genres { get; set; }
        public int? Id { get; set; }

        [Required(ErrorMessage = "El nombre la pelicula es requerido.")]
        [StringLength(255)]
        public string Name { get; set; }
        [Display(Name = "Genre")]
        [Required]
        public byte? GenreId { get; set; }
        [Display(Name = "Release Date")]
        [Required(ErrorMessage = "La fecha de estreno es obligatoria.")]
        public DateTime DateRelease { get; set; }
        [Display(Name = "Number In Stock")]
        [Range(1, 20)]
        [Required]
        public int? NumberInStock { get; set; }

        public string Title
        {
            get { return Id != 0 ? "Edit Movie" : "New Movie"; }
        }

        public MovieFormViewModel()
        {
            Id = 0;
        }

        public MovieFormViewModel(Movie movie)
        {
            Id = movie.Id;
            Name = movie.Name;
            DateRelease = movie.DateRelease;
            GenreId = movie.GenreId;
            NumberInStock = movie.NumberInStock;
        }
    }
}